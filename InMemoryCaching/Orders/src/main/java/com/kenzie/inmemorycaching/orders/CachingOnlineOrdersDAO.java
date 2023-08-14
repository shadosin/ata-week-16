package com.kenzie.inmemorycaching.orders;

import java.util.ArrayList;
import java.util.List;

import com.kenzie.inmemorycaching.orders.models.Order;

public class CachingOnlineOrdersDAO {


    /**
     * Constructor.
     * PARTICIPANTS: Instantiate a LoadingCache instance
     * @param ordersDAO OnlineOrdersDAO that will be used by the cache to retrieve a miss.
     */
    public CachingOnlineOrdersDAO(OnlineOrdersDAO ordersDAO) {

    }

    /**
     * Gets all the orders associated with a particular user.
     * PARTICIPANTS: call the cache to get the list of orders
     * @param userId user to retrieve orders for
     * @return List of orders
     */
    public List<Order> getOrdersByUser(String userId) {
        return new ArrayList<Order>();
    }
}
