package events.newevents;

import events.EventArgs;
import kitchen.OrderStatus;

public class OrderChangedEvent extends EventArgs<OrderChangedEvent> {
  private int orderNumber;
  private OrderStatus newStatus;

  public OrderChangedEvent(int orderNumber, OrderStatus newStatus) {
    super(OrderChangedEvent.class);
    this.orderNumber = orderNumber;
    this.newStatus = newStatus;
  }

  public int getOrderNumber() {
    return orderNumber;
  }

  public OrderStatus getNewStatus() {
    return newStatus;
  }
}
