package events.eventtypes;

import com.fasterxml.jackson.annotation.JsonProperty;
import events.EventArgs;
import kitchen.*;

public class OrderInputEvent extends EventArgs<OrderInputEvent> {

    private Order order;

    public OrderInputEvent(Order order) {
        super(OrderInputEvent.class);
        this.order = order;
    }

  public Order getOrder() {
    return order;
  }

  public void setOrder(Order order) {
    this.order = order;
  }

}
