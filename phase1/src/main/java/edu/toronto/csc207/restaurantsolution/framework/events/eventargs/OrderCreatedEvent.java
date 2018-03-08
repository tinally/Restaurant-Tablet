package edu.toronto.csc207.restaurantsolution.framework.events.eventargs;

import edu.toronto.csc207.restaurantsolution.framework.events.EventArgs;
import edu.toronto.csc207.restaurantsolution.model.Order;

/**
<<<<<<< HEAD:phase1/src/events/newevents/OrderCreatedEvent.java
 * OrderCreatedEvent represents the event for creating the order.
=======
 * An event to trigger creation of an order.
>>>>>>> 6c2ba67e6d43c990817f7455d710287af5146462:phase1/src/main/java/edu/toronto/csc207/restaurantsolution/framework/events/eventargs/OrderCreatedEvent.java
 */
public class OrderCreatedEvent extends EventArgs<OrderCreatedEvent> {

    /**
     * The new Order to be created.
     */
    private Order newOrder;

    /**
     * Default constructor for OrderCreatedEvent.
     */
    public OrderCreatedEvent() {
        super(OrderCreatedEvent.class);
    }

    /**
     * Returns the Order created.
     *
     * @return the Order created
     */
    public Order getNewOrder() {
        return newOrder;
    }

    /**
     * Sets the Order created.
     *
     * @param newOrder the Order to be set
     */
    public void setNewOrder(Order newOrder) {
        this.newOrder = newOrder;
    }
}
