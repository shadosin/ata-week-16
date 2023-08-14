package com.kenzie.lambdaexpressions.shippinghelper;
import java.util.ArrayList;
import java.util.List;

/**
 * Processes orders, determining which are available for automated
 * single-item fulfillment.
 */
public class ShippingHelper {

    /**
     * Main method. When completed it should print to standard output a list of orders with only one item.
     * @param args Parameters for main method.
     */
    public static void main(String[] args) {
        ShippingHelper shippingHelper = new ShippingHelper();
        OrderManager orderManager = new OrderManager();
        List<List<Integer>> orderList = orderManager.getOrderList();
        orderList = shippingHelper.checkOrders(orderList);

        System.out.println(orderList);
    }

    /**
     * Removes orders larger than one item from a list.
     * @param orderList A list of orders.
     * @return A list of orders that contain only one item.
     */
    public List<List<Integer>> checkOrders(List<List<Integer>> orderList) {
        List<List<Integer>> orders = new ArrayList<>(orderList);
        // TODO Use list's removeIf method to remove orders larger than one item. Use a lambda expression to implement
        //  the predicate interface. Use the removeIf method on the list called orders, a copy of the argument
        //  orderList.

        return orders;
    }
}
