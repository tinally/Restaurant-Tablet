package events.eventtypes;

import events.EventArgs;
import kitchen.*;

public class OrderCompleteEvent extends EventArgs<OrderCompleteEvent> {
    private Order order;

    public OrderCompleteEvent(Order order) {
        super(OrderCompleteEvent.class);
        this.order = order;
    }

    public Order getOrder() {
        return this.order;
    }
}
