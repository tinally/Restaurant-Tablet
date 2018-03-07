package services;

import events.EventArgs;
import events.EventEmitter;
import events.newevents.OrderChangedEvent;
import events.newevents.OrderCreatedEvent;
import kitchen.Order;
import kitchen.OrderStatus;
import restaurant.MenuItem;
import services.framework.Service;
import services.framework.ServiceConstructor;

import java.util.*;
import java.util.stream.Collectors;

public class OrderManagerService extends Service {

    private final EventEmitter emitter;
    private Map<Integer, Order> orders;

    @ServiceConstructor
    public OrderManagerService(EventEmitter emitter) {
        this.emitter = emitter;
        emitter.registerEventHandler(this::saveOrder, OrderCreatedEvent.class);
        emitter.registerEventHandler(this::updateOrder, OrderChangedEvent.class);
        this.orders = new HashMap<>();
    }

    private void updateOrder(OrderChangedEvent e) {
        // Ensure that the order status matches with the event status.
        // May not need this after removing event.txt
        this.getOrder(e.getOrderNumber()).setStatus(e.getNewStatus());
    }

    public Order createOrder(int tableNumber, String serverName, MenuItem menuItem) {
        Order order = new Order(menuItem, tableNumber, serverName, new Random().nextInt());
        OrderCreatedEvent event = new OrderCreatedEvent();
        event.setNewOrder(order);
        emitter.onEvent(event);
        return order;
    }

    private void saveOrder(OrderCreatedEvent event) {
        Order order = event.getNewOrder();
        this.orders.put(order.getOrderNumber(), order);
    }

    public Order getOrder(int orderNumber) {
        return orders.get(orderNumber);
    }

    public Collection<Order> getAllOrders() {
        return orders.values();
    }

    public List<Order> getOrdersForTableNumber(int tableNumber) {
        return orders.values().stream().filter(o -> o.getTableNumber() == tableNumber).collect(Collectors.toList());
    }

    public void notifyOrderStatusChanged(int orderNumber, OrderStatus newStatus, String sender) {
        OrderChangedEvent event = new OrderChangedEvent(orderNumber, newStatus);
        event.setSender(sender);
        emitter.onEvent(event);
    }
}
