package edu.toronto.csc207.restaurantsolution.framework.events.eventargs;

import edu.toronto.csc207.restaurantsolution.framework.events.EventArgs;
import edu.toronto.csc207.restaurantsolution.model.Order;

/**
 * OrderCreatedEvent represents the event for creating the order.
 */
public class OrderCreatedEvent extends EventArgs<OrderCreatedEvent> {
  /**
   * The new Order to be created.
   */
  private Order newOrder;

  /**
   * Default constructor for OrderCreatedEvent.
   */
  public OrderCreatedEvent() {
    super(OrderCreatedEvent.class);
  }

  /**
   * Returns the Order created.
   *
   * @return the Order created
   */
  public Order getNewOrder() {
    return newOrder;
  }

  /**
   * Sets the Order created.
   *
   * @param newOrder the Order to be set
   */
  public void setNewOrder(Order newOrder) {
    this.newOrder = newOrder;
  }
}
