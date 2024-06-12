package org.cdc_demo.data_faker.util;

import java.util.List;

import org.cdc_demo.data_faker.avro_generated.Order;
import org.cdc_demo.data_faker.avro_generated.Shipment;

import com.github.javafaker.Faker;

public class DecisionTree {
    private Faker faker = new Faker();

    public static enum Decision {
        CREATE_ORDER,
        CREATE_SHIPMENT,
        CANCEL_ORDER,
        PROGRESS_SHIPMENT
    }

    public Decision getDecision(List<Tuple<Long, Order>> orders, List<Tuple<Long, Shipment>> shipments) {
        var has_existing_order = orders.size() > 0;
        var has_existing_shipment = shipments.size() > 0;
        var all_orders_completed = orders.stream().allMatch(o -> o.y.getOrderStatus() == "COMPLETED" || o.y.getOrderStatus() == "CANCELLED");

        // If the customer has no order, create one
        if (!has_existing_order) {
            return Decision.CREATE_ORDER;
        }

        // If the customer has no shipment, create one
        if (!has_existing_shipment) {
            return Decision.CREATE_SHIPMENT;
        }

        // If there is an unfinished order, make a decision
        // The unfinished order will either be PENDING or SHIPPED
        if (!all_orders_completed) {
            var action = faker.number().numberBetween(0, 10);

            var order_shipped = orders.stream().anyMatch(o -> o.y.getOrderStatus() == "SHIPPED");

            // If the unfinished order has been shipped, progress the shipment
            // otherwise, decide to cancel the order or progress the shipment
            if (order_shipped || action<9) {
                return Decision.PROGRESS_SHIPMENT;
            } else {
                return Decision.CANCEL_ORDER;
            }
        } else {
            // If all orders are completed, create a new order
            return Decision.CREATE_ORDER;
        }
    }
}
