package edu.toronto.csc207.restaurantsolution.model.serialization;

import com.j256.ormlite.stmt.query.In;
import edu.toronto.csc207.restaurantsolution.model.interfaces.Ingredient;
import edu.toronto.csc207.restaurantsolution.model.interfaces.MenuItem;
import edu.toronto.csc207.restaurantsolution.model.interfaces.Order;

import java.time.Instant;
import java.util.*;

public class OrderImpl implements Order {
  private UUID orderId;
  private Integer tableNumber;
  private Integer orderNumber;
  private MenuItem menuItem;
  private Double orderCost;
  private Instant orderDate;
  private List<Ingredient> removals;
  private Map<Ingredient, Integer> additions;


  @Override
  public UUID getOrderID() {
    return this.orderId;
  }

  @Override
  public Integer getTableNumber() {
    return this.tableNumber;
  }

  @Override
  public Integer getOrderNumber() {
    return this.orderNumber;
  }

  @Override
  public MenuItem getMenuItem() {
    return this.menuItem;
  }

  @Override
  public List<Ingredient> getRemovals() {
    return this.removals;
  }

  @Override
  public Map<Ingredient, Integer> getAdditions() {
    return this.additions;
  }

  @Override
  public Double getOrderCost() {
    return this.orderCost;
  }

  @Override
  public Instant getOrderTimestamp() {
    return this.orderDate;
  }

  public void setOrderId(UUID orderId) {
    this.orderId = orderId;
  }

  public void setTableNumber(Integer tableNumber) {
    this.tableNumber = tableNumber;
  }

  public void setOrderNumber(Integer orderNumber) {
    this.orderNumber = orderNumber;
  }

  public void setMenuItem(MenuItem menuItem) {
    this.menuItem = menuItem;
  }

  public void setOrderCost(Double orderCost) {
    this.orderCost = orderCost;
  }

  public void setOrderDate(Instant orderDate) {
    this.orderDate = orderDate;
  }

  public void setRemovals(List<Ingredient> removals) {
    this.removals = removals;
  }

  public void setAdditions(Map<Ingredient, Integer> additions) {
    this.additions = additions;
  }
}
