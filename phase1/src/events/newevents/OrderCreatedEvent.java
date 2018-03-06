package events.newevents;

import events.EventArgs;
import kitchen.Order;

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
