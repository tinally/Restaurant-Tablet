package events.eventtypes;

import events.EventArgs;
import kitchen.Order;

public class OrderReturnedEvent extends EventArgs<OrderReturnedEvent> {

  private Order order;
  private String reason;

  public OrderReturnedEvent(Order order, String reason) {
    super(OrderReturnedEvent.class);
    this.order = order;
    this.reason = reason;
  }

  public OrderReturnedEvent() {
    super(OrderReturnedEvent.class);
  }

  public Order getOrder() {
    return order;
  }

  public void setOrder(Order order) {
    this.order = order;
  }

}
