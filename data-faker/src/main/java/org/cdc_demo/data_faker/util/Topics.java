package org.cdc_demo.data_faker.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.avro.specific.SpecificRecord;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.cdc_demo.data_faker.avro_generated.Address;
import org.cdc_demo.data_faker.avro_generated.Contact;
import org.cdc_demo.data_faker.avro_generated.Customer;
import org.cdc_demo.data_faker.avro_generated.Order;
import org.cdc_demo.data_faker.avro_generated.OrderLineItem;
import org.cdc_demo.data_faker.avro_generated.Product;
import org.cdc_demo.data_faker.avro_generated.Shipment;
import org.cdc_demo.data_faker.avro_generated.TableId;
import org.cdc_demo.data_faker.biz.KafkaEnv;

import io.confluent.kafka.schemaregistry.client.SchemaRegistryClientConfig;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import lombok.Getter;

@Getter
public class Topics {
    //private Topic<RegistrationId, DemandData> demand;
    //private Topic<RegistrationId, RegistrationData> reg;
    private Topic<TableId, Address> addresses;
    private Topic<TableId, Contact> contacts;
    private Topic<TableId, Customer> customers;
    private Topic<TableId, Order> orders;
    private Topic<TableId, OrderLineItem> orderLineItems;
    private Topic<TableId, Product> products;
    private Topic<TableId, Shipment> shipments;

    public static String ADDRESSES_TOPIC = "addresses";
    public static String CONTACTS_TOPIC = "contacts";
    public static String CUSTOMERS_TOPIC = "customers";
    public static String ORDERS_TOPIC = "orders";
    public static String ORDER_LINE_ITEMS_TOPIC = "order-line-items";
    public static String PRODUCTS_TOPIC = "products";
    public static String SHIPMENTS_TOPIC = "shipments";

    private <T extends SpecificRecord> SpecificAvroSerde<T> initSerde(Map<String, String> props, boolean isKey) {
        var serde = new SpecificAvroSerde<T>();
        serde.configure(props, isKey);
        return serde;
    }

    private <K extends SpecificRecord, V extends SpecificRecord> Topic<K, V> initTopic(String name, Map<String, String> props) {
        return Topic.<K, V>builder()
            .name(name)
            .keySerde(this.initSerde(props, true))
            .valueSerde(this.initSerde(props, false))
            .build();
    }

    public Topics(KafkaEnv kafka_config) {
        var props = new HashMap<String, String>();

        props.put("schema.registry.url", kafka_config.getSchemaRegistryUrl());
        props.put(SchemaRegistryClientConfig.BASIC_AUTH_CREDENTIALS_SOURCE, "USER_INFO");
        props.put(
            SchemaRegistryClientConfig.USER_INFO_CONFIG, 
            new StringBuilder()
                .append(kafka_config.getSchemaRegistryUser())
                .append(":")
                .append(kafka_config.getSchemaRegistryPass())
                .toString()
        );

        this.addresses = initTopic(ADDRESSES_TOPIC, props);
        this.contacts = initTopic(CONTACTS_TOPIC, props);
        this.customers = initTopic(CUSTOMERS_TOPIC, props);
        this.orders = initTopic(ORDERS_TOPIC, props);
        this.orderLineItems = initTopic(ORDER_LINE_ITEMS_TOPIC, props);
        this.products = initTopic(PRODUCTS_TOPIC, props);
        this.shipments = initTopic(SHIPMENTS_TOPIC, props);
    }
}