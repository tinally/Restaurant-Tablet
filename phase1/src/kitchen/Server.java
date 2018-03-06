package kitchen;

import events.EventEmitter;
import events.RestaurantEventHandler;
import events.eventtypes.IngredientRequiresReorderEvent;
import events.eventtypes.OrderCompleteEvent;
import events.eventtypes.OrderInputEvent;
import events.eventtypes.OrderRejectEvent;
import services.BillPrinterService;
import services.OrderManagerService;
import services.PaymentManagerService;
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
     * The table the Server is responsible for.
     */
    private Table table;

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
    private OrderManagerService orderManager;

    /**
     * Manages the payment.
     */
    private PaymentManagerService paymentManager;

    /**
     * Class constructor specifying the emitter, printer, name, table, inventory, and manager.
     *
     * @param emitter     main event handler
     * @param printer     the printer responsible for this bill
     * @param name        name of the Server
     * @param table the table number the Server is responsible for
     * @param inventory   inventory of all ingredients
     * @param orderManager     manager of the orders
     * @param paymentManager    manager of the payment
     */
    @ServiceConstructor
    public Server(EventEmitter emitter, BillPrinterService printer, String name, Table table, Inventory inventory,
                  OrderManagerService orderManager, PaymentManagerService paymentManager) {
        this.emitter = emitter;
        this.printer = printer;
        this.name = name;
        this.table = table;
        this.inventory = inventory;
        this.orderManager = orderManager;
        this.paymentManager = paymentManager;
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
        // Adds the order to the managers
    }

    public void serve() {
        // Change status
    }

    public void checkout() {
        printer.printBill();
    }
}
