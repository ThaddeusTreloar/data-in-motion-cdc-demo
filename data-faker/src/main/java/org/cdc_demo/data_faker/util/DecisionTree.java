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

    public Decision getDecision(List<Tuple<Long, Order>> orders) {
        var has_existing_order = orders.size() > 0;

        var pending_order = orders.stream().anyMatch(o -> o.y.getOrderStatus() == "PENDING");
        var shipped_order = orders.stream().anyMatch(o -> o.y.getOrderStatus() == "SHIPPED");

        if (!has_existing_order) {
            // If the customer has no order, create one
            return Decision.CREATE_ORDER;
        } else if (pending_order) {
            // If the customer has a pending order, decide whether to create a shipment or cancel the order
            var action = faker.number().numberBetween(0, 10);

            if (action < 9) {
                return Decision.CREATE_SHIPMENT;
            } else {
                return Decision.CANCEL_ORDER;
            }
        } else if (shipped_order) {
            // If the customer has a shipped order, progress the shipment
            return Decision.PROGRESS_SHIPMENT;
        } else {
            // Otherwise, create a new order
            return Decision.CREATE_ORDER;
        }
    }
}
