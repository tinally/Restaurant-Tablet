package events.eventtypes;

import events.EventArgs;
import restaurant.*;

public class OrderCompleteEvent extends EventArgs<OrderCompleteEvent> {
    private OrderItem oi;

    public OrderCompleteEvent(OrderItem oi) {
        super(OrderCompleteEvent.class);
        this.oi = oi;
    }

    public OrderItem getOrderItem() {
        return this.oi;
    }
}
