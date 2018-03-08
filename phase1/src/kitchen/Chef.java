package kitchen;

import events.EventEmitter;
import events.newevents.OrderChangedEvent;
import restaurant.MenuItem;
import services.OrderManagerService;

import java.util.Map;

/**
 * Chef represents the cook of the restaurant.
 */
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
//    this.emitter.registerEventHandler(e -> {
//      if (e.getNewStatus() == OrderStatus.SEEN) {
//        completeOrder(manager.getOrder(e.getOrderNumber()));
//      }
//    }, OrderChangedEvent.class);
    }


    /**
     * The Chef completes an order and creates an OrderCompleteEvent if the inventory has enough ingredients needed.
     * Otherwise, the Chef ends the order and creates an OrderRejectEvent.
     */
    private void completeOrder(Order order) {
        MenuItem mi = order.getMenuItem();
        Map<Ingredient, Integer> ingredients = mi.getIngredients();
        for (Ingredient i : ingredients.keySet()) {
            int deduct = ingredients.get(i);
            int current = this.inventory.getAmountRemaining(i);
            if (current < deduct) {
                manager.notifyOrderStatusChanged(order.getOrderNumber(), OrderStatus.REJECTED, "Chef " + name);
                return;
            }
        }
        manager.notifyOrderStatusChanged(order.getOrderNumber(), OrderStatus.FILLED, "Chef " + name);
    }
}
