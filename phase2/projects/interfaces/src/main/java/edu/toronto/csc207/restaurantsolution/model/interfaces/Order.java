package edu.toronto.csc207.restaurantsolution.model.interfaces;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Represents an Order in the restaurant.
 */
public interface Order extends Serializable {
  /**
   * Returns an unique ID for an Order.
   *
   * @return an unique ID for an Order
   */
  UUID getOrderID();

  /**
   * Returns the table number of this Order.
   *
   * @return the table number of this Order
   */
  Integer getTableNumber();

  void setTableNumber(Integer tableNumber);

  /**
   * Returns the order number of this Order.
   *
   * @return the order number of this Order
   */
  Integer getOrderNumber();

  void setOrderNumber(Integer orderNumber);

  /**
   * Return the MenuItem corresponding to this Order.
   *
   * @return the corresponding MenuItem
   */
  MenuItem getMenuItem();

  void setMenuItem(MenuItem menuItem);

  /**
   * Returns the list of Ingredients needed for this Order.
   *
   * @return the list of Ingredients
   */
  List<Ingredient> getRemovals();

  void setRemovals(List<Ingredient> removals);

  /**
   * Returns the map of Ingredients added with the corresponding amount.
   *
   * @return the map of Ingredient and Integer
   */
  Map<Ingredient, Integer> getAdditions();

  void setAdditions(Map<Ingredient, Integer> additions);

  /**
   * Returns the cost of this Order.
   *
   * @return the cost of this Order
   */
  Double getOrderCost();

  void setOrderCost(Double orderCost);

  /**
   * Returns the time the Order was created.
   *
   * @return the time the Order was created
   */
  Instant getOrderTimestamp();

  /**
   * Returns the name of the User who created this Order.
   *
   * @return the name of the User who created this Order
   */
  String getCreatingUser();

  void setCreatingUser(String creatingUser);

  /**
   * Gets the status of the Order
   *
   * @return the status of the Order
   */
  OrderStatus getOrderStatus();

  void setOrderStatus(OrderStatus orderStatus);

  void setOrderId(UUID orderId);

  void setOrderDate(Instant orderDate);
}
