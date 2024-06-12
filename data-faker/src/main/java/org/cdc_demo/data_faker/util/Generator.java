package org.cdc_demo.data_faker.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.cdc_demo.data_faker.avro_generated.Address;
import org.cdc_demo.data_faker.avro_generated.Contact;
import org.cdc_demo.data_faker.avro_generated.Customer;
import org.cdc_demo.data_faker.avro_generated.Order;
import org.cdc_demo.data_faker.avro_generated.OrderLineItem;
import org.cdc_demo.data_faker.avro_generated.Product;
import org.cdc_demo.data_faker.avro_generated.Shipment;

import com.github.javafaker.Faker;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor @Getter
public class Generator {
    private Faker faker = new Faker();

    public static int CUSTOMER_POOL_SIZE = 10;
    public static int PRODUCT_POOL_SIZE = 100;
	public static String DEFAULT_ORDER_STATE = "PENDING";
	public static String DEFAULT_SHIPMENT_STATE = "SHIPPED";

	public static String SHIPPING_ADDRESS_TYPE = "SHIPPING";
	public static String BILLING_ADDRESS_TYPE = "BILLING";

	public static String INSERT_ACTION = "INSERT";
    public static String UPDATE_ACTION = "UPDATE";

	public long generateLong(int max) {
		return faker.number().numberBetween(0, max-1);
	}

	public Customer generateCustomer() {
		return Customer.newBuilder()
			.setCustomerName(faker.name().fullName())
			.setDob(faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
			.setTitle(faker.name().title())
			.build();
	}

	public Contact generateContact(long customerId, Customer customer) {
		var email = customer.getCustomerName().toLowerCase().replace(" ", ".") + "@example.com";
		var twitter_handle = customer.getCustomerName().replace(" ", "") + faker.number().numberBetween(1, 100);
		var instagram_handle = customer.getCustomerName().replace(" ", ".") + faker.number().numberBetween(1, 100);


		return Contact.newBuilder()
			.setCustomerId(customerId)
			.setEmail(email)
			.setMobile(faker.phoneNumber().phoneNumber())
			.setTwitter(twitter_handle)
			.setInstagram(instagram_handle)
			.build();
	}

	public Address generateShippingAddress(long customerId) {
		return Address.newBuilder()
			.setCustomerId(customerId)
			.setAddressType(SHIPPING_ADDRESS_TYPE)
			.setStreetNumber(faker.address().buildingNumber())
			.setStreetName(faker.address().streetName())
			.setCity(faker.address().city())
			.setState(faker.address().state())
			.setCountry(faker.address().country())
			.setPostcode(faker.address().zipCode())
			.build();
	}

	public Address generateBillingAddress(long customerId, Address shippingAddress) {
		return Address.newBuilder()
			.setCustomerId(customerId)
			.setAddressType(BILLING_ADDRESS_TYPE)
			.setStreetNumber(shippingAddress.getStreetNumber())
			.setStreetName(shippingAddress.getStreetName())
			.setCity(shippingAddress.getCity())
			.setState(shippingAddress.getState())
			.setCountry(shippingAddress.getCountry())
			.setPostcode(shippingAddress.getPostcode())
			.build();
	}

	public Address generateBillingAddress(long customerId) {
		return Address.newBuilder()
			.setCustomerId(customerId)
			.setAddressType(BILLING_ADDRESS_TYPE)
			.setStreetNumber(faker.address().buildingNumber())
			.setStreetName(faker.address().streetName())
			.setCity(faker.address().city())
			.setState(faker.address().state())
			.setCountry(faker.address().country())
			.setPostcode(faker.address().zipCode())
			.build();
	}

	public Product generateProduct() {
		return Product.newBuilder()
			.setProductName(faker.commerce().productName())
			.setProductDescription(faker.commerce().material())
			.setListPrice((float) faker.number().randomDouble(2, 10, 100))
			.build();
	}

	public Order generateOrder(long customer_id, long shipping_address_id, int item_count) {
		return Order.newBuilder()
			.setCustomerId(customer_id)
			.setShippingAddress(shipping_address_id)
			.setOrderDate(LocalDate.now())
			.setOrderStatus(DEFAULT_ORDER_STATE)
			.setUpdatedOn(Instant.now())
			.setLineItemCount(item_count)
			.setStatementAction(INSERT_ACTION)
			.build();
	}

	public Shipment generateShipment(long orderId) {
		return Shipment.newBuilder()
			.setOrderId(orderId)
			.setCreatedOn(Instant.now())
			.setShippedOn(Instant.now())
			.setShipmentStatus(DEFAULT_SHIPMENT_STATE)
			.setUpdatedOn(Instant.now())
			.setStatementAction(INSERT_ACTION)
			.build();
	}

	public OrderLineItem generateOrderLineItem(long orderId, long productId, float productPrice) {
		var quantity = faker.number().numberBetween(1, 10);

		return OrderLineItem.newBuilder()
			.setOrderId(orderId)
			.setProductId(productId)
			.setQuantity(quantity)
			.setLineTotal(quantity * productPrice)
			.build();
	}

	public ArrayList<OrderLineItem> generateOrderItems(long order_id, HashMap<Long, Product> products) {
		var no_items = faker.number().numberBetween(1, 5);

		ArrayList<Integer> order_products = new ArrayList<Integer>(
			IntStream.range(0, no_items)
				.mapToObj(i -> faker.number().numberBetween(0, PRODUCT_POOL_SIZE-1))
				.collect(Collectors.toSet())
		);

		no_items = order_products.size();

		var quantities = IntStream.range(0, no_items)
			.mapToObj(i -> faker.number().numberBetween(1, 10))
			.toList();

		var prices = IntStream
			.range(0, no_items)
			.mapToObj(i -> products.get((long) order_products.get(i)).getListPrice() * quantities.get(i))
			.toList();

		ArrayList<OrderLineItem> items = new ArrayList<OrderLineItem>();

		for (int i = 0; i < no_items; i++) {
			items.add(
				OrderLineItem.newBuilder()
					.setOrderId(order_id)
					.setProductId(order_products.get(i))
					.setQuantity(quantities.get(i))
					.setLineTotal(prices.get(i))
					.build()
			);
		}

		return items;
	}
}
