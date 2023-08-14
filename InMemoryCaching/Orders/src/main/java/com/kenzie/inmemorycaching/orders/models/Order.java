package com.kenzie.inmemorycaching.orders.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "OnlineOrders")
public class Order {
    private String userId;
    private String orderId;
    private Double cost;

    /**
     * Constructor for instantiating outside of DynamoDB.
     * @param userId User id
     * @param orderId Order id
     * @param cost total order cost
     */
    public Order(String userId, String orderId, Double cost) {
        this.userId = userId;
        this.orderId = orderId;
        this.cost = cost;
    }

    /**
     * Basic constructor for DynamoDB to instantiate the Class with.
     */
    public Order() {

    }

    @DynamoDBHashKey(attributeName = "user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @DynamoDBRangeKey(attributeName = "order_id")
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @DynamoDBAttribute(attributeName = "cost")
    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Order{" +
            "userId='" + userId + '\'' +
            ", orderId='" + orderId + '\'' +
            ", cost=" + cost +
            '}';
    }
}
