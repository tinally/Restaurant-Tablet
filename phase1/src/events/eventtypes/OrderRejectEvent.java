package events.eventtypes;

import com.fasterxml.jackson.annotation.JsonProperty;
import events.EventArgs;
import kitchen.*;

public class OrderRejectEvent extends EventArgs<OrderRejectEvent> {

    private Order order;

    public OrderRejectEvent(Order order) {
        super(OrderRejectEvent.class);
        this.order = order;
    }

    public OrderRejectEvent() {
        super(OrderRejectEvent.class);
    }

    public Order getOrder() {
        return order;
    }

    public String reason() {
        return "Sorry, " + order.getMenuItem().getName() + " is sold out.";
    }

}
