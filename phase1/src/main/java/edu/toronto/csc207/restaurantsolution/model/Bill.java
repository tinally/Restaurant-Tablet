package edu.toronto.csc207.restaurantsolution.model;

import java.util.ArrayList;

/**
 * A collection of outstanding orders that are to be paid.
 */
public class Bill {
  /**
   * The order items that are outstanding (to be paid for) with this bill.
   */
  private ArrayList<Order> orders;

  /**
   * Constructs a new empty bill.
   */
  public Bill() {
    orders = new ArrayList<>();
  }

  /**
   * Returns the order items associated with this bill.
   *
   * @return the order items on this bill.
   */
  public ArrayList<Order> getOrders() {
    return orders;
  }

  /**
   * Adds an order to this bill.
   *
   * @param order the order to be added.
   */
  public void addOrder(Order order) {
    orders.add(order);
  }
}
