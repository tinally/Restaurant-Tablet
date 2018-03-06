package events.eventtypes;

import com.fasterxml.jackson.annotation.JsonProperty;
import events.EventArgs;
import kitchen.Order;

public class OrderSeenEvent extends EventArgs<OrderSeenEvent> {

  private Order order;

  public OrderSeenEvent(Order order) {
    super(OrderSeenEvent.class);
    this.order = order;
  }

  public OrderSeenEvent() {
    super(OrderSeenEvent.class);
  }

  public Order getOrder() {
    return order;
  }

  public void setOrder(Order order) {
    this.order = order;
  }

}
