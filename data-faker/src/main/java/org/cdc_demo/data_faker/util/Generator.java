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

	public Contact generateContact(long customerId) {
		return Contact.newBuilder()
			.setCustomerId(customerId)
			.setEmail(faker.internet().emailAddress())
			.setMobile(faker.phoneNumber().phoneNumber())
			.setTwitter(faker.name().username())
			.setInstagram(faker.name().username())
			.build();
	}

	public Address generateAddress(long customerId) {
		return Address.newBuilder()
			.setCustomerId(customerId)
			.setAddressType("HOME")
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

	public Order generateOrder(long customerId) {
		return Order.newBuilder()
			.setCustomerId(customerId)
			.setShippingAddress(customerId)
			.setOrderDate(LocalDate.now())
			.setOrderStatus("PENDING")
			.setUpdatedOn(Instant.now())
			.build();
	}

	public Shipment generateShipment(long orderId) {
		return Shipment.newBuilder()
			.setOrderId(orderId)
			.setCreatedOn(Instant.now())
			.setShippedOn(Instant.now())
			.setShipmentStatus("PENDING")
			.setUpdatedOn(Instant.now())
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

		var quantities = IntStream.range(0, no_items)
			.mapToObj(i -> faker.number().numberBetween(1, 10))
			.toList();

		var prices = IntStream
			.range(0, no_items)
			.mapToObj(i -> products.get((long)i).getListPrice() * quantities.get(i))
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
