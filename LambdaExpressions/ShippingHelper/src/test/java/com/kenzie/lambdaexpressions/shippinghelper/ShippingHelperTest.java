package com.kenzie.lambdaexpressions.shippinghelper;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ShippingHelperTest {

    @Test
    public void checkNumbers_withFourOrders_calculatesNumberOfOrders() {
        // GIVEN
        ShippingHelper shippingHelper = new ShippingHelper();
        List<List<Integer>> orderList = new ArrayList<List<Integer>>();
        List<List<Integer>> singleOrderTestList = new ArrayList<List<Integer>>();
        List<Integer> order1 = new ArrayList<>();
        order1.add(245611);
        List<Integer> order2 = new ArrayList<>();
        order2.add(109321);
        order2.add(912311);
        List<Integer> order3 = new ArrayList<>();
        order3.add(426881);
        List<Integer> order4 = new ArrayList<>();
        order4.add(112677);
        List<Integer> order5 = new ArrayList<>();
        order5.add(199312);
        order5.add(724227);

        orderList.add(order1);
        orderList.add(order2);
        orderList.add(order3);
        orderList.add(order4);
        orderList.add(order5);

        singleOrderTestList.add(order1);
        singleOrderTestList.add(order3);
        singleOrderTestList.add(order4);

        // WHEN
        List<List<Integer>> singleOrders = shippingHelper.checkOrders(orderList);

        // THEN
        assertEquals(singleOrderTestList.size(), singleOrders.size(), "Received the wrong number of single " +
                "orders: Expected " + singleOrderTestList + ", received " + singleOrders);
        assertTrue(singleOrders.containsAll(singleOrderTestList), "Received the wrong single orders: " +
                "Expected " + singleOrderTestList + ", received " + singleOrders);
    }
}
