package edu.toronto.csc207.restaurantsolution.model.implementations;

import edu.toronto.csc207.restaurantsolution.model.interfaces.Ingredient;
import edu.toronto.csc207.restaurantsolution.model.interfaces.MenuItem;
import edu.toronto.csc207.restaurantsolution.model.interfaces.Order;
import edu.toronto.csc207.restaurantsolution.model.interfaces.OrderStatus;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Represents an implementation of Order.
 */
public class OrderImpl implements Order {
  private UUID orderId;
  private Integer tableNumber;
  private Integer orderNumber;
  private MenuItem menuItem;
  private Double orderCost;
  private Instant orderDate;
  private List<Ingredient> removals;
  private Map<Ingredient, Integer> additions;
  private String creatingUser;
  private OrderStatus orderStatus;

  public OrderImpl() {
  }

  @Deprecated
  public OrderImpl(MenuItem menuItem, int tableNumber, String serverName, int orderNumber) {
    this.setMenuItem(menuItem);
    this.setTableNumber(tableNumber);
    this.setCreatingUser(serverName);
    this.setOrderNumber(orderNumber);
    this.setOrderStatus(OrderStatus.CREATED);
  }

  @Override
  public UUID getOrderID() {
    return this.orderId;
  }

  @Override
  public Integer getTableNumber() {
    return this.tableNumber;
  }

  @Override
  public void setTableNumber(Integer tableNumber) {
    this.tableNumber = tableNumber;
  }

  @Override
  public Integer getOrderNumber() {
    return this.orderNumber;
  }

  @Override
  public void setOrderNumber(Integer orderNumber) {
    this.orderNumber = orderNumber;
  }

  @Override
  public MenuItem getMenuItem() {
    return this.menuItem;
  }

  @Override
  public void setMenuItem(MenuItem menuItem) {
    this.menuItem = menuItem;
  }

  @Override
  public List<Ingredient> getRemovals() {
    return this.removals;
  }

  @Override
  public void setRemovals(List<Ingredient> removals) {
    this.removals = removals;
  }

  @Override
  public Map<Ingredient, Integer> getAdditions() {
    return this.additions;
  }

  @Override
  public void setAdditions(Map<Ingredient, Integer> additions) {
    this.additions = additions;
  }

  @Override
  public Double getOrderCost() {
    return this.orderCost;
  }

  @Override
  public void setOrderCost(Double orderCost) {
    this.orderCost = orderCost;
  }

  @Override
  public Instant getOrderTimestamp() {
    return this.orderDate;
  }

  @Override
  public String getCreatingUser() {
    return this.creatingUser;
  }

  @Override
  public void setCreatingUser(String creatingUser) {
    this.creatingUser = creatingUser;
  }

  @Override
  public OrderStatus getOrderStatus() {
    return this.orderStatus;
  }

  @Override
  public void setOrderStatus(OrderStatus orderStatus) {
    this.orderStatus = orderStatus;
  }

  @Override
  public void setOrderId(UUID orderId) {
    this.orderId = orderId;
  }

  @Override
  public void setOrderDate(Instant orderDate) {
    this.orderDate = orderDate;
  }

  @Override
  public String toString() {
    return "Order #" + this.getOrderNumber() + " " + this.getMenuItem().getName();
  }
}
