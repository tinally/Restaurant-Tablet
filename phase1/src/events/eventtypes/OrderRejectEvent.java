package events.eventtypes;

import com.fasterxml.jackson.annotation.JsonProperty;
import events.EventArgs;
import restaurant.*;

public class OrderRejectEvent extends EventArgs<OrderRejectEvent> {

    private OrderItem oi;

    public OrderRejectEvent(OrderItem oi) {
        super(OrderRejectEvent.class);
        this.oi = oi;
    }

    public OrderRejectEvent() {
        super(OrderRejectEvent.class);
    }

    public OrderItem getOrder() {
        return oi;
    }

    public String reason() {
        return "Sorry, " + oi.getMenuItem().getName() + " is sold out.";
    }

}
