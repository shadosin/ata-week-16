package com.kenzie.lambdaexpressions.customermanager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Provides access to customer "datastore" for the Consumer activity.
 */
public class DatabaseManager {

    private Map<String, Boolean> customers = new HashMap<>();
    private List<String> emailedCustomers = new ArrayList<>();

    /**
     * Constructor for DatabaseManager.
     */
    public DatabaseManager() {
        customers.put("Diego Ramirez", true);
        customers.put("Carlos Salazar", false);
        customers.put("Alejandro Rosalez", true);
        customers.put("John Stiles", true);
        customers.put("Li Juan", false);
    }

    /**
     * Getter for customer list.
     * @return List of customers
     */
    public List<String> getCustomers() {
        return new ArrayList<>(customers.keySet());
    }

    /**
     * Determines if a customer has accepted new terms and conditions.
     * @param name String of the customer.
     * @return boolean for whether the customer has accepted the new terms and customers. Returns null if no customer
     * exists for the given name
     */
    public boolean checkCustomer(String name) {
        return customers.get(name);
    }

    /**
     * Handles emailing customers.
     * @param name Customer passed to method.
     */
    public void emailCustomer(String name) {
        emailedCustomers.add(name);
    }

    /**
     * Getter for list of emailed customers.
     * @return List of Strings of customers emailed.
     */
    public List<String> getEmailedCustomers() {
        return emailedCustomers;
    }
}
