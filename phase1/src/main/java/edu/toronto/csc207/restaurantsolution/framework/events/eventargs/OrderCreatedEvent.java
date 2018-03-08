package edu.toronto.csc207.restaurantsolution.framework.events.eventargs;

import edu.toronto.csc207.restaurantsolution.framework.events.EventArgs;
import edu.toronto.csc207.restaurantsolution.model.Order;

/**
 * An event to trigger creation of an order.
 */
public class OrderCreatedEvent extends EventArgs<OrderCreatedEvent> {
  private Order newOrder;

  public OrderCreatedEvent() {
    super(OrderCreatedEvent.class);
  }

  public Order getNewOrder() {
    return newOrder;
  }

  public void setNewOrder(Order newOrder) {
    this.newOrder = newOrder;
  }
}
