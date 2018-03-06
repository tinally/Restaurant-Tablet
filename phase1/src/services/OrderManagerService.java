package services;

import events.EventEmitter;
import events.newevents.OrderCreatedEvent;
import kitchen.Order;
import restaurant.MenuItem;
import services.framework.Service;
import services.framework.ServiceConstructor;

import java.util.*;
import java.util.stream.Collectors;

public class OrderManagerService extends Service {
  private final EventEmitter em;
  private Map<Integer, Order> orders;

  @ServiceConstructor
  OrderManagerService(EventEmitter em) {
    this.em = em;
    em.registerEventHandler(this::saveOrder, OrderCreatedEvent.class);
    this.orders = new HashMap<>();
  }

  public Order createOrder(int tableNumber, String serverName, MenuItem menuItem) {
    Order order = new Order(menuItem, tableNumber, serverName, new Random().nextInt());
    OrderCreatedEvent event = new OrderCreatedEvent();
    event.setNewOrder(order);
    em.onEvent(event);
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
}
