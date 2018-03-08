package tests;

import edu.toronto.csc207.restaurantsolution.framework.events.EventArgs;

public class OrderInputStringEvent extends EventArgs<OrderInputStringEvent> {
  private String order;

  public OrderInputStringEvent(String order) {
    super(OrderInputStringEvent.class);
    this.order = order;
  }

  public String getOrder() {
    return order;
  }
}
