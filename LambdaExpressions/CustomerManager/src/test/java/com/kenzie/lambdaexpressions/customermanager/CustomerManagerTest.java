package com.kenzie.lambdaexpressions.customermanager;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CustomerManagerTest {

    @Test
    public void checkCustomers_withTwoCustomers_successfullyEmailsCustomers() {
        // GIVEN
        List<String> customersToEmail = new ArrayList<>();
        customersToEmail.add("Carlos Salazar");
        customersToEmail.add("Li Juan");

        CustomerManager customerManager = new CustomerManager();

        // WHEN
        List<String> emailedCustomers = customerManager.checkCustomers();

        // THEN
        assertEquals(customersToEmail.size(), emailedCustomers.size(), "Received the wrong number of " +
                "emailed customers: Expected " + customersToEmail + ", received " + emailedCustomers);
        assertTrue(emailedCustomers.containsAll(customersToEmail), "Received the wrong emailed customers: " +
                "Expected " + customersToEmail + ", received " + emailedCustomers);
    }
}
