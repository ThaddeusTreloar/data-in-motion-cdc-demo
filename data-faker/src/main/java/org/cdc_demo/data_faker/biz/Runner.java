package org.cdc_demo.data_faker.biz;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.avro.specific.SpecificRecord;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.cdc_demo.data_faker.avro_generated.Address;
import org.cdc_demo.data_faker.avro_generated.Contact;
import org.cdc_demo.data_faker.avro_generated.Customer;
import org.cdc_demo.data_faker.avro_generated.Order;
import org.cdc_demo.data_faker.avro_generated.OrderLineItem;
import org.cdc_demo.data_faker.avro_generated.Product;
import org.cdc_demo.data_faker.avro_generated.Shipment;
import org.cdc_demo.data_faker.avro_generated.TableId;
import org.cdc_demo.data_faker.util.DecisionTree;
import org.cdc_demo.data_faker.util.Generator;
import org.cdc_demo.data_faker.util.Topic;
import org.cdc_demo.data_faker.util.Topics;
import org.cdc_demo.data_faker.util.Tuple;
import org.cdc_demo.data_faker.util.DecisionTree.Decision;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Runner {
    // Producer for each topic
    private KafkaProducer<TableId, Customer> customer_producer;
    private KafkaProducer<TableId, Contact> contact_producer;
    private KafkaProducer<TableId, Address> address_producer;
    private KafkaProducer<TableId, Product> product_producer;
    private KafkaProducer<TableId, Order> order_producer;
    private KafkaProducer<TableId, Shipment> shipment_producer;
    private KafkaProducer<TableId, OrderLineItem> order_line_item_producer;

    // Data structures to store initial data
    private HashMap<Long, Address> addresses = new HashMap<>();
    private HashMap<Long, Contact> contacts = new HashMap<>();
    private HashMap<Long, Customer> customers = new HashMap<>();
    private HashMap<Long, Product> products = new HashMap<>();

    // Data structures to store ongoing data
    private ArrayList<OrderLineItem> order_line_items = new ArrayList<OrderLineItem>();
    private ArrayList<Order> orders = new ArrayList<Order>();
    private ArrayList<Shipment> shipments = new ArrayList<Shipment>();

    private Generator generator = new Generator();

    private Topics topics;

    private static <K extends SpecificRecord, V extends SpecificRecord> KafkaProducer<K, V> initProducer(
            Topic<K, V> topic, Properties props) {
        return new KafkaProducer<K, V>(
                props,
                topic.getKeySerde().serializer(),
                topic.getValueSerde().serializer());
    }

    private void createOrder(long cusomer_id) {
        var order_id = (long) this.orders.size();

        // Create order line items
        var order_line_items = generator
            .generateOrderItems(order_id, this.products);

        var order = Order.newBuilder()
                .setCustomerId(cusomer_id)
                .setOrderDate(LocalDate.now())
                .setOrderStatus("PENDING")
                .setShippingAddress(cusomer_id)
                .setUpdatedOn(Instant.now())
                .build();

        order_line_items
            .forEach(order_line_item -> {
                try {
                    var order_line_id = (long) this.order_line_items.size();

                    var response = order_line_item_producer.send(
                        new ProducerRecord<>(
                            topics.getOrderLineItems().getName(),
                            TableId.newBuilder().setId(order_line_id).build(),
                            order_line_item));
                    
                    log.info("Created Order Line Item, line_id: {}, body: {}", order_line_id, order_line_item);
    
                    response.get();

                    this.order_line_items.add(order_line_item);
                } catch (Exception e) {
                    log.error("Error: {}", e);
                }
            });
        
        try {
            var response = order_producer.send(
                    new ProducerRecord<>(
                            topics.getOrders().getName(),
                            TableId.newBuilder().setId(order_id).build(),
                            order));

            log.info("Created Order, order_id: {}, body: {}", order_id, order);

            response.get();

            this.orders.add(order);
        } catch (Exception e) {
            log.error("Error: {}", e);
        }
    }

    private void createShipment(long order_id) {
        // Catch any faults in our logic.
        // If there is already a shipment, progress the shipment instead.
        var order_has_shipment = this.shipments.stream()
            .anyMatch(shipment -> shipment.getOrderId() == order_id);

        if (order_has_shipment) {
            var shipment_id = IntStream
                .range(0, this.shipments.size())
                .filter(i -> this.shipments.get(i).getOrderId() == order_id)
                .findFirst()
                .getAsInt();

            progressShipment(shipment_id);
        }

        // Otherwise, create a shipment
        var shipment = generator.generateShipment(order_id);

        try {
            var response = shipment_producer.send(
                    new ProducerRecord<>(
                            topics.getShipments().getName(),
                            TableId.newBuilder().setId((long) this.shipments.size()).build(),
                            shipment));

            log.info("Shipment, shipment_id: {}, body: {}", this.shipments.size(), shipment);

            response.get();

            this.shipments.add(shipment);
        } catch (Exception e) {
            log.error("Error: {}", e);
        }

        // Update the order
        updateOrder(order_id, "SHIPPED");
    }

    private void updateOrder(long order_id, String status) {
        var order = this.orders.get((int) order_id);

        order.setOrderStatus(status);
        order.setUpdatedOn(Instant.now());

        try {
            var response = order_producer.send(
                    new ProducerRecord<>(
                            topics.getOrders().getName(),
                            TableId.newBuilder().setId(order_id).build(),
                            order));

            log.info("Updated Order, order_id: {}, body: {}", order_id, order);

            response.get();
        } catch (Exception e) {
            log.error("Error: {}", e);
        }
    }

    private void progressShipment(long shipment_id) {
        var shipment = this.shipments.get((int) shipment_id);

        shipment.setShipmentStatus("DELIVERED");
        shipment.setUpdatedOn(Instant.now());

        try {
            var response = shipment_producer.send(
                    new ProducerRecord<>(
                            topics.getShipments().getName(),
                            TableId.newBuilder().setId(shipment_id).build(),
                            shipment));

            log.info("Updated Shipment, shipment_id: {}, body: {}", shipment_id, shipment);

            response.get();
        } catch (Exception e) {
            log.error("Error: {}", e);
        }

        updateOrder(shipment.getOrderId(), "COMPLETED");
    }

    private void cancelOrder(long order_id) {
        // Catch any faults in our logic.
        // If there is already a shipment, progress the shipment instead.
        var order_has_shipment = this.shipments.stream()
            .anyMatch(shipment -> shipment.getOrderId() == order_id);

        if (order_has_shipment) {
            var shipment_id = IntStream
                .range(0, this.shipments.size())
                .filter(i -> this.shipments.get(i).getOrderId() == order_id)
                .findFirst()
                .getAsInt();

            progressShipment(shipment_id);
        }

        // Otherwise, cancel the order
        var order = this.orders.get((int) order_id);

        order.setOrderStatus("CANCELLED");
        order.setUpdatedOn(Instant.now());

        try {
            var response = order_producer.send(
                    new ProducerRecord<>(
                            topics.getOrders().getName(),
                            TableId.newBuilder().setId(order_id).build(),
                            order));

            log.info("Cancelled Order, order_id: {}, body: {}", order_id, order);

            response.get();
        } catch (Exception e) {
            log.error("Error: {}", e);
        }
    }

    public Runner(KafkaEnv config) {
        this.topics = new Topics(config);
        var props = config.intoConfigMap();

        this.customer_producer = initProducer(this.topics.getCustomers(), props);
        this.contact_producer = initProducer(this.topics.getContacts(), props);
        this.address_producer = initProducer(this.topics.getAddresses(), props);
        this.product_producer = initProducer(this.topics.getProducts(), props);
        this.order_producer = initProducer(this.topics.getOrders(), props);
        this.shipment_producer = initProducer(this.topics.getShipments(), props);
        this.order_line_item_producer = initProducer(this.topics.getOrderLineItems(), props);
    }

    public void run() {
        // Initialise customers, contacts, addresses, and products
        try {
            for (long i = 0; i < Generator.CUSTOMER_POOL_SIZE; i++) {
                var table_id = TableId.newBuilder().setId(i).build();
                var customer = generator.generateCustomer();
                var contact = generator.generateContact(table_id.getId(), customer);
                var address = generator.generateAddress(table_id.getId());

                customer_producer.send(new ProducerRecord<>(topics.getCustomers().getName(), table_id, customer));
                contact_producer.send(new ProducerRecord<>(topics.getContacts().getName(), table_id, contact));
                address_producer.send(new ProducerRecord<>(topics.getAddresses().getName(), table_id, address));

                log.info("Table ID: {}", table_id);
                log.info("Customer: {}", customer);
                log.info("Contact: {}", contact);
                log.info("Address: {}", address);

                customers.put(table_id.getId(), customer);
                contacts.put(table_id.getId(), contact);
                addresses.put(table_id.getId(), address);
            }

            for (long i = 0; i < Generator.PRODUCT_POOL_SIZE; i++) {
                var table_id = TableId.newBuilder().setId(i).build();
                var product = generator.generateProduct();

                product_producer.send(new ProducerRecord<>(topics.getProducts().getName(), table_id, product));

                log.info("Table ID: {}", table_id);
                log.info("Product: {}", product);

                products.put(table_id.getId(), product);
            }
        } catch (Exception e) {
            System.out.println(e);
            System.exit(0);
        } finally {
            customer_producer.close();
            contact_producer.close();
            address_producer.close();
            product_producer.close();
        }

        // Stream orders and shipments
        try {
            var decicion_tree = new DecisionTree();

            while (true) {
                var cusomer_id = generator.generateLong(Generator.CUSTOMER_POOL_SIZE);

                List<Tuple<Long, Order>> existing_orders = IntStream
                        .range(0, orders.size())
                        .mapToObj(i -> new Tuple<>((long) i, orders.get(i)))
                        .filter(order_tuple -> order_tuple.y.getCustomerId() == cusomer_id)
                        .collect(Collectors.toList());

                List<Tuple<Long, Shipment>> existing_shipments = IntStream
                        .range(0, shipments.size())
                        .mapToObj(i -> new Tuple<>((long) i, shipments.get(i)))
                        .filter(
                                shipment_tuple -> existing_orders.stream()
                                        .anyMatch(
                                                order_tuple -> order_tuple.x.equals(shipment_tuple.y.getOrderId())))
                        .collect(Collectors.toList());

                var decision = decicion_tree.getDecision(existing_orders, existing_shipments);
                
                var idx_last_order = existing_orders.size() - 1;
                var idx_last_shipment = existing_shipments.size() - 1;

                switch (decision) {
                    case CREATE_ORDER:
                        createOrder(cusomer_id);
                        break;
                    case CREATE_SHIPMENT:
                        createShipment(existing_orders.get(idx_last_order).x);
                        break;
                    case CANCEL_ORDER:
                        cancelOrder(existing_orders.get(idx_last_order).x);
                        break;
                    case PROGRESS_SHIPMENT:
                        progressShipment(existing_shipments.get(idx_last_shipment).x);
                        break;
                    default:
                        break;
                }

                Thread.sleep(1000);
            }
        } catch (Exception e) {
            System.out.println(e);
            System.exit(0);
        } finally {
            order_producer.close();
            shipment_producer.close();
            order_line_item_producer.close();
        }
    }
}
