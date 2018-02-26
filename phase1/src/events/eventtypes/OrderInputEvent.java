package events.eventtypes;

import events.EventArgs;

public class OrderInputEvent extends EventArgs<OrderInputEvent> {
  private String order;

  public OrderInputEvent(String order) {
    super(OrderInputEvent.class);
    this.order = order;
  }

  public String getOrder() {
    return order;
  }
}
