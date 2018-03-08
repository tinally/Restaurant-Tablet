package edu.toronto.csc207.restaurantsolution.model;

/**
 * OrderStatus contains all the possible statuses of an Order.
 */
public enum OrderStatus {
  /**
   * The initial state of an order.
   */
  CREATED,
  /**
   * The order was inputted into the system.
   */
  INPUTTED,
  /**
   * An order was pushed to the kitchen for chefs to confirm.
   */
  PUSHED,
  /**
   * An order was seen by a chef.
   */
  SEEN,
  /**
   * An order was filled by a chef and is ready to be delivered to the customer.
   */
  FILLED,
  /**
   * An order was rejected by the chef, and the chef is unable to complete the order.
   */
  REJECTED,
  /**
   * The order was delivered to the customer and the customer will pay for the order.
   */
  DELIVERED,
  /**
   * The order was returned by the customer to the kitchen.
   */
  RETURNED
}
