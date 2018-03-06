package services;

import kitchen.Order;
import restaurant.OrderItem;
import services.framework.Service;
import services.framework.ServiceConstructor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class OrderManagerService extends Service {
    private Map<Integer, Order> orders;
    private int numOrders = 0;

    @ServiceConstructor
    OrderManagerService() {
        this.orders = new HashMap<>();
    }

    public Order createOrder(int tableNumber, String serverName, OrderItem... orderItems) {
        Order order = new Order(Arrays.asList(orderItems), tableNumber, serverName, ++numOrders);
        this.orders.put(numOrders, order);
        return order;
    }

    public Order getOrder(int orderNumber) {
        return orders.get(orderNumber);
    }
}
