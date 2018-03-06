package kitchen;

import events.EventEmitter;
import events.RestaurantEventHandler;
import events.eventtypes.IngredientRequiresReorderEvent;
import events.eventtypes.OrderCompleteEvent;
import events.eventtypes.OrderInputEvent;
import events.eventtypes.OrderRejectEvent;
import services.BillPrinterService;
import services.OrderManagerService;
import services.framework.*;
import restaurant.*;
import payment.*;

import java.util.*;

public class Server extends Service {

    /**
     * Name of the Server.
     */
    private String name;

    /**
     * The table table the Server is responsible for.
     */
    private int tableNumber;

    /**
     * Handles the events.
     */
    private EventEmitter emitter;

    /**
     * Prints the bill for the Server.
     */
    private BillPrinterService printer;

    /**
     * Inventory of all ingredients of this restaurant.
     */
    private Inventory inventory;

    /**
     * Manages the orders.
     */
    private OrderManagerService manager;

    /**
     * Class constructor specifying the emitter, printer, name, tableNumber, inventoyr, and manager.
     *
     * @param emitter     main event handler
     * @param printer     the printer responsible for this bill
     * @param name        name of the Server
     * @param tableNumber the table number the Server is responsible for
     * @param inventory   inventory of all ingredients
     * @param manager     manager of the orders
     */
    @ServiceConstructor
    public Server(EventEmitter emitter, BillPrinterService printer, String name, int tableNumber, Inventory inventory,
                  OrderManagerService manager) {
        this.emitter = emitter;
        this.printer = printer;
        this.name = name;
        this.tableNumber = tableNumber;
        this.inventory = inventory;
        this.manager = manager;
        emitter.registerEventHandler(this::updateIngredient, OrderCompleteEvent.class);
        emitter.registerEventHandler(this::rejectOrderItem, OrderRejectEvent.class);
    }

    /**
     * Updates the ingredient when an OrderCompleteEvent is filed.
     *
     * @param event the event called after an order is completed by the Chef
     */
    private void updateIngredient(OrderCompleteEvent event) {
        Order order = event.getOrder();
        MenuItem mi = order.getMenuItem();
        Map<Ingredient, Integer> ingredients = mi.getIngredients();
        for (Ingredient i : ingredients.keySet()) {
            int deduct = ingredients.get(i);
            this.inventory.removeFromInventory(i, deduct);
        }
    }

    /**
     * Rejects the order when an OrderRejectEvent is filed.
     *
     * @param event the event called after an order is rejected by the Chef
     */
    private void rejectOrderItem(OrderRejectEvent event) {

    }

    public void addOrder() {
        // Send orders to Chef
    }

    public void serve() {
        // Change status
    }

    public void checkout() {
        printer.printBill();
    }
}
