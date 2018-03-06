package events.eventtypes;

import events.EventArgs;
import kitchen.Order;

public class OrderDeliveredEvent extends EventArgs<OrderDeliveredEvent> {

  private Order order;

  public OrderDeliveredEvent(Order order) {
    super(OrderDeliveredEvent.class);
    this.order = order;
  }

  public OrderDeliveredEvent() {
    super(OrderDeliveredEvent.class);
  }

  public Order getOrder() {
    return order;
  }

  public void setOrder(Order order) {
    this.order = order;
  }

}
