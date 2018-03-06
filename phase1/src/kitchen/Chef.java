package kitchen;

import java.util.*;

import events.EventEmitter;
import events.eventtypes.*;
import restaurant.*;
import services.OrderManagerService;

public class Chef {

    /**
     * Name the of the Chef.
     */
    private String name;

    /**
     * Manages the orders.
     */
    private OrderManagerService manager;

    /**
     * Handles the events.
     */
    private EventEmitter emitter;

    /**
     * Inventory of all the ingredients of this restaurant.
     */
    private Inventory inventory;

    /**
     * Class constructor specifying the name, emitter, inventory, and manager.
     *
     * @param name      name of the Chef
     * @param emitter   main event handler
     * @param inventory inventory of all ingredients
     * @param manager   manager of the orders
     */
    public Chef(String name, EventEmitter emitter, Inventory inventory, OrderManagerService manager) {
        this.name = name;
        this.emitter = emitter;
        this.inventory = inventory;
        this.manager = manager;
    }

    /**
     * Handles an OrderInputEvent.
     * @param event the event called after an order is inputted
     */
    public void handle(OrderInputEvent event) {
        this.receiveOrder(event.getOrder());
    }

    /**
     * The Chef receives an order from a Server.
     *
     * @param order order received
     */
    private void receiveOrder(Order order) {
        manager.createOrder(order);
    }

    /**
     * The Chef completes an order and creates an OrderCompleteEvent if the inventory has enough ingredients needed.
     * Otherwise, the Chef creates an OrderRejectEvent and doesn't make the order.
     */
    public void completeOrder() {
        for (Order o : ) {
            List<OrderItem> oi = o.getItems();
            for (OrderItem item : oi) {
                MenuItem mi = item.getMenuItem();
                Map<Ingredient, Integer> inventory = this.inventory.getInventory();
                Map<Ingredient, Integer> ingredients = mi.getIngredients();
                for (Ingredient i : ingredients.keySet()) {
                    int deduct = ingredients.get(i);
                    int current = inventory.get(i);
                    if (current >= deduct) {
                        OrderCompleteEvent event = new OrderCompleteEvent(item);
                        emitter.onEvent(event);
                    } else {
                        OrderRejectEvent event = new OrderRejectEvent(item);
                        emitter.onEvent(event);
                    }
                }
            }
        }
    }

}
