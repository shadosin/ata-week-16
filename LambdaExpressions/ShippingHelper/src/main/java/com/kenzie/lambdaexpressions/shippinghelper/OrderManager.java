package com.kenzie.lambdaexpressions.shippinghelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides access to the Order data.
 */
public class OrderManager {

    private List<List<Integer>> orderList;

    /**
     * Constructor, ensures that order data are available.
     */
    public OrderManager() {
        orderList = new ArrayList<List<Integer>>();
        List<Integer> order1 = new ArrayList<>();
        order1.add(245611);

        List<Integer> order2 = new ArrayList<>();
        order2.add(109321);
        order2.add(912311);
        order2.add(741262);

        List<Integer> order3 = new ArrayList<>();
        order3.add(219361);
        order3.add(426881);

        List<Integer> order4 = new ArrayList<>();
        order4.add(112855);
        order4.add(222561);

        List<Integer> order5 = new ArrayList<>();
        order5.add(334617);

        orderList.add(order1);
        orderList.add(order2);
        orderList.add(order3);
        orderList.add(order4);
        orderList.add(order5);
    }

    /**
     * Getter for list of orders.
     * @return A list of orders.
     */
    public List<List<Integer>> getOrderList() {
        return orderList;
    }
}
