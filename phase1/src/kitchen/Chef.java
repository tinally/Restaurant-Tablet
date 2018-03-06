package kitchen;

import java.util.*;

import events.EventEmitter;

public class Chef {

    private String name;
    private Queue<Order> items;
    private EventEmitter em;

    public Chef(String name, EventEmitter em) {
        this.name = name;
        this.em = em;
        items = new Queue<Order>();
    }

    public void handle(OrderInputEvent event) {
        this.receiveOrder(event.getOrder());
    }

    private void receiveOrder(Order o) {
        items.add(o);
    }

    public void completeOrder() {
        for (Order o : items) {
            List<OrderItem> oi = o.getItems();
            for (OrderItem item : oi) {
                OrderCompleteEvent event = new OrderCompleteEvent(item);
                em.onEvent(event, this);
            }
        }
    }

    public Queue<Order> getOrders(String name) {
        if (this.name.equals(name)) {
            return items;
        }
        return null;
    }

}
