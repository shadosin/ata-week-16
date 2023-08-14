package com.kenzie.inmemorycaching.orders;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.QueryResultPage;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.kenzie.inmemorycaching.orders.models.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OnlineOrdersDAO {
    private final DynamoDBMapper mapper;

    /**
     * Constructor.
     * @param mapper instance of DynamoDBMapper to use for DDB calls
     */
    public OnlineOrdersDAO(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }

    /**
     *
     * @param userId user to retrieve orders for
     * @return List of Order objects
     */
    public List<Order> getOrdersByUser(String userId) {
        Map<String, AttributeValue> valueMap = new HashMap<>();
        valueMap.put(":userId", new AttributeValue().withS(userId));
        DynamoDBQueryExpression<Order> queryExpression = new DynamoDBQueryExpression<Order>()
                .withKeyConditionExpression("user_id = :userId")
                .withExpressionAttributeValues(valueMap);

        System.out.println("Getting orders from DynamoDB for user " + userId);

        QueryResultPage<Order> queryList = mapper.queryPage(Order.class, queryExpression);
        List<Order> orders = queryList.getResults();
        if (orders == null) {
            return new ArrayList<Order>();
        }
        return orders;
    }

}
