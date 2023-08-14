package com.kenzie.lambdaexpressions.customermanager;

import java.util.List;

/**
 * Responsible for checking which customers have accepted the new terms
 * and conditions, emailing those who have not.
 */
public class CustomerManager {

    /**
     * Main method of project. When completed it should print to standard output a list of emailed customers.
     * @param args Main method parameter
     */
    public static void main(String[] args) {
        CustomerManager customerManager = new CustomerManager();
        List<String> emailedCustomers = customerManager.checkCustomers();
        for (String customer : emailedCustomers) {
            System.out.println("Emailed " + customer);
        }
    }

    /**
     * Checks if customers have accepted new terms and conditions.
     * @return List of Strings of customers emailed.
     */
    public List<String> checkCustomers() {
        DatabaseManager databaseManager = new DatabaseManager();
        // TODO Get Customers from DatabaseManager using the method getCustomers

        // TODO Use List's forEach() method with a lambda expression to check the status for each customer,
        //  emailing each customer who has not accepted the new terms.

        return databaseManager.getEmailedCustomers();
    }
}
