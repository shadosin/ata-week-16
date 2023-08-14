package com.kenzie.inmemorycaching.orders;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.QueryResultPage;
import com.google.common.cache.LoadingCache;
import com.kenzie.inmemorycaching.orders.models.Order;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class CachingOnlineOrdersDAOTest {

    @Mock
    DynamoDBMapper mapper;

    @Mock
    QueryResultPage queryResult;

    @BeforeEach
    public void setup() {
        initMocks(this);
    }

    @Test
    public void loadingCacheField_existsWithInstance() {
        //GIVEN
        OnlineOrdersDAO orderDAO = new OnlineOrdersDAO(mapper);
        CachingOnlineOrdersDAO cachingDAO = new CachingOnlineOrdersDAO(orderDAO);

        //WHEN
        // Using reflection to detect a LoadingCache field and verify that it was initialized.
        Field loadingCacheField = getLoadingCacheField(cachingDAO);
        Object cache;
        try {
            cache = loadingCacheField.get(cachingDAO);
        } catch (IllegalAccessException | NullPointerException e) {
            cache = null;
        }

        //THEN
        Assertions.assertNotNull(cache, "LoadingCache field was not instantiated correctly.");
        Assertions.assertEquals(LoadingCache.class,
                loadingCacheField.getType(), "No field of type LoadingCache found in the " +
                        "CachingOnlineOrderDAO");
    }

    @Test
    public void loadingCacheField_correctKeyValueTypes() {
        //GIVEN
        OnlineOrdersDAO orderDAO = new OnlineOrdersDAO(mapper);
        CachingOnlineOrdersDAO cachingDAO = new CachingOnlineOrdersDAO(orderDAO);

        //WHEN
        // Using reflection to detect a LoadingCache field and verify that it was initialized.
        Field loadingCacheField = getLoadingCacheField(cachingDAO);
        LoadingCache<String, List<Order>> cache = null;
        try {
            cache = (LoadingCache) loadingCacheField.get(cachingDAO);
        } catch (IllegalAccessException | NullPointerException e) {
            //The IllegalAccessException is thrown when attempting to cast a class of one type into an incompatible type
            Assertions.fail("Expected a loading cache in the CachingOnlineOrderDAO class with correct key structure.");
        }

        //THEN
        Assertions.assertNotNull(cache, "LoadingCache field was not instantiated correctly.");
    }

    @Test
    public void getOrdersByUsers_fromDatabase_correctListLength() {
        //GIVEN
        Order order1 = new Order("62da86ba", "2398982u3", 83.23);
        Order order2 = new Order("62da86ba", "239872383", 63.22);
        List<Order> mockOrderList = new ArrayList<>();
        mockOrderList.add(order1);
        mockOrderList.add(order2);

        when(mapper.queryPage(eq(Order.class), any())).thenReturn(queryResult);
        when(queryResult.getResults()).thenReturn(mockOrderList);

        OnlineOrdersDAO orderDAO = new OnlineOrdersDAO(mapper);
        CachingOnlineOrdersDAO cachingDAO = new CachingOnlineOrdersDAO(orderDAO);

        //WHEN
        List<Order> orders = cachingDAO.getOrdersByUser("62da86ba");

        //THEN
        Assertions.assertEquals(mockOrderList, orders, "The List returned by the CachingOrderDAO was not correct.");
    }

    @Test
    public void getOrdersByUsers_fromDatabase_itemInCache() {
        //GIVEN
        Order order1 = new Order("62da86ba", "2398982u3", 83.23);
        Order order2 = new Order("62da86ba", "239872383", 63.22);
        List<Order> mockOrderList = new ArrayList<>();
        mockOrderList.add(order1);
        mockOrderList.add(order2);

        when(mapper.queryPage(eq(Order.class), any())).thenReturn(queryResult);
        when(queryResult.getResults()).thenReturn(mockOrderList);

        OnlineOrdersDAO orderDAO = new OnlineOrdersDAO(mapper);
        CachingOnlineOrdersDAO cachingDAO = new CachingOnlineOrdersDAO(orderDAO);

        //WHEN
        List<Order> orders = cachingDAO.getOrdersByUser("62da86ba");

        // Using reflection to get the instance of LoadingCache and verify that the requested
        // item made it into the cache.
        Field loadingCacheField = getLoadingCacheField(cachingDAO);

        LoadingCache<String, List<Order>> cache;
        List<Order> ordersInCache = null;
        int cacheSize = 0;
        try {
            cache = (LoadingCache) loadingCacheField.get(cachingDAO);
            Map<String, List<Order>> cacheMap = cache.asMap();
            cacheSize = cacheMap.size();

            if (cacheSize == 1) {
                Map.Entry<String, List<Order>> entry = cacheMap.entrySet().iterator().next();
                ordersInCache = entry.getValue();
            }
        } catch (IllegalAccessException | NullPointerException e) {
            //The IllegalAccessException is thrown when attempting to cast a class of one type into an incompatible type
            cache = null;
        }

        //THEN
        Assertions.assertEquals(1, cacheSize, "The List returned by LoadingCache was not stored in " +
                "the cache.");
        Assertions.assertEquals(mockOrderList, orders, "The List returned by the cache is different from what was " +
                "returned from the database.");
        Assertions.assertEquals(mockOrderList, ordersInCache, "The List stored in the cache is different from what " +
                "was returned from the database.");
    }

    @Test
    public void getOrdersByUsers_fromCacheAfterMultipleAdds_correctCacheSize() {
        //GIVEN
        Order order1 = new Order("62da86ba", "2398982u3", 83.23);
        Order order2 = new Order("62da86ba", "239872383", 63.22);
        List<Order> mockOrderList = new ArrayList<>();
        mockOrderList.add(order1);
        mockOrderList.add(order2);

        Order order3 = new Order("62da86ba", "2398982u3", 83.23);
        List<Order> mockOrderList2 = new ArrayList<>();
        mockOrderList2.add(order3);

        when(mapper.queryPage(eq(Order.class), any())).thenReturn(queryResult);

        OnlineOrdersDAO orderDAO = new OnlineOrdersDAO(mapper);
        CachingOnlineOrdersDAO cachingDAO = new CachingOnlineOrdersDAO(orderDAO);

        //WHEN
        // Testing adding multiple items to the cache. Have to swap out the mock for each call.
        when(queryResult.getResults()).thenReturn(mockOrderList);
        List<Order> orders = cachingDAO.getOrdersByUser("62da86ba");

        when(queryResult.getResults()).thenReturn(mockOrderList2);
        List<Order> differentOrders = cachingDAO.getOrdersByUser("d1a93bea");

        when(queryResult.getResults()).thenReturn(mockOrderList);
        List<Order> ordersFromCache = cachingDAO.getOrdersByUser("62da86ba");

        // Using reflection to check the size of the actual cache.
        Field loadingCacheField = getLoadingCacheField(cachingDAO);
        LoadingCache<String, List<Order>> cache;
        int cacheSize = 0;
        try {
            cache = (LoadingCache) loadingCacheField.get(cachingDAO);
            Map<String, List<Order>> cacheMap = cache.asMap();
            cacheSize = cacheMap.size();
        } catch (IllegalAccessException |  NullPointerException e) {
            //The IllegalAccessException is thrown when attempting to cast a class of one type into an incompatible type
            cache = null;
        }

        //THEN
        Assertions.assertEquals(2, cacheSize, "The size of the cache is not correct. There should be " +
                "two items in it but there is: " + cacheSize);
        Assertions.assertEquals(mockOrderList, orders, "The List returned from the cache was not the same " +
                "as the one returned from the database.");
        Assertions.assertEquals(mockOrderList, ordersFromCache, "The List returned from the cache was not the same " +
                "after adding more items to the cache.");
        Assertions.assertEquals(mockOrderList2, differentOrders, "The second List returned from the cache was not " +
                "the same as what was returned from the database.");
    }

    /**
     * Uses reflection to access the private LoadingCache field
     * This is only being done for test verification purposes.
     * @param cachingDAO Instance of the CachingOnlineOrderDAO
     * @return the private field that should contain the LoadingCache cache.
     */
    private Field getLoadingCacheField(CachingOnlineOrdersDAO cachingDAO) {
        Class<?> cachingClass = cachingDAO.getClass();
        Field[] fields = cachingClass.getDeclaredFields();
        Field loadingCacheField = null;
        for (Field field : fields) {
            if (field.getType().equals(LoadingCache.class)) {
                loadingCacheField = field;
                field.setAccessible(true);
            }
        }
        return loadingCacheField;
    }

}
