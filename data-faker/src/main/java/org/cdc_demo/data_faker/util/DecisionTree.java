package org.cdc_demo.data_faker.util;

import com.github.javafaker.Faker;

public class DecisionTree {
    private Faker faker = new Faker();

    public static enum Decision {
        CREATE_ORDER,
        CREATE_SHIPMENT,
        UPDATE_ORDER,
        CANCEL_ORDER,
        PROGRESS_SHIPMENT
    }

    public Decision getDecision(boolean has_existing_order, boolean has_existing_shipment) {
        if (!has_existing_order) {
            return Decision.CREATE_ORDER;
        } else if (!has_existing_shipment) {
            var action = faker.number().numberBetween(0, 2);

            if (action==0) {
                return Decision.CREATE_SHIPMENT;
            } else if (action==1) {
                return Decision.UPDATE_ORDER;
            } else {
                return Decision.CREATE_ORDER;
            }
        } else {
            var action = faker.number().numberBetween(0, 3);

            if (action==0) {
                return Decision.CREATE_SHIPMENT;
            } else if (action==1) {
                return Decision.UPDATE_ORDER;
            } else if (action==2) {
                return Decision.CREATE_ORDER;
            } else {
                return Decision.PROGRESS_SHIPMENT;
            }
        }
    }
}
