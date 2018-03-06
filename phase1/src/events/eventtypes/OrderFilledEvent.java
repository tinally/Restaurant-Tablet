package events.eventtypes;

import events.EventArgs;
import kitchen.Order;

public class OrderFilledEvent extends EventArgs<OrderFilledEvent> {

  private Order order;

  public OrderFilledEvent(Order order) {
    super(OrderFilledEvent.class);
    this.order = order;
  }

  public OrderFilledEvent() {
    super(OrderFilledEvent.class);
  }

  public Order getOrder() {
    return order;
  }

  public void setOrder(Order order) {
    this.order = order;
  }

}
