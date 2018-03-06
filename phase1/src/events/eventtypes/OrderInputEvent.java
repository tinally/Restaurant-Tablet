package events.eventtypes;

import com.fasterxml.jackson.annotation.JsonProperty;
import events.EventArgs;
import kitchen.Order;

public class OrderInputEvent extends EventArgs<OrderInputEvent> {

  private final Order order;

  public OrderInputEvent(@JsonProperty("order") Order order) {
    super(OrderInputEvent.class);
    this.order = order;
  }

  public Order getOrder() {
    return order;
  }
}
