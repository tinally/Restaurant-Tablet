package edu.toronto.csc207.restaurantsolution.model.interfaces;

/**
 * OrderStatus contains all the possible statuses of an Order.
 */
public enum OrderStatus {
  /**
   * The initial state of an order.
   */
  CREATED,
  /**
   * An order was seen by a chef.
   */
  SEEN,
  /**
   * An order was filled by a chef and is ready to be delivered to the customer.
   */
  FILLED,
  /**
   * The order was delivered to the customer and the customer will pay for the order.
   */
  DELIVERED,
  /**
   * The order was returned by the customer to the kitchen.
   */
  RETURNED,
  /**
   * The order was paid for and billed.
   */
  PAID
}
