package kitchen;

import events.EventEmitter;
import events.newevents.OrderChangedEvent;
import services.BillPrinterService;
import services.OrderManagerService;
import services.PaymentManagerService;
import services.framework.*;
import restaurant.*;

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
     * @param emitter        main event handler
     * @param printer        the printer responsible for this bill
     * @param name           name of the Server
     * @param table          the table number the Server is responsible for
     * @param inventory      inventory of all ingredients
     * @param orderManager   manager of the orders
     * @param paymentManager manager of the payment
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
        emitter.registerEventHandler(this::updateIngredient, OrderChangedEvent.class);
        emitter.registerEventHandler(this::rejectOrderItem, OrderChangedEvent.class);
    }

    /**
     * Updates the ingredient when an OrderCompleteEvent is filed.
     *
     * @param event the event called after an order is completed by the Chef
     */
    private void updateIngredient(OrderChangedEvent event) {
        if (event.getNewStatus() != OrderStatus.COMPLETED) return;
        Order order = orderManager.getOrder(event.getOrderNumber());
        if (order == null) return;
        ;
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
    private void rejectOrderItem(OrderChangedEvent event) {
        if (event.getNewStatus() == OrderStatus.REJECTED) {
            Order order = orderManager.getOrder(event.getOrderNumber());
            System.out.println(order.getOrderNumber() + " is rejected.");
        }
    }

    public void addOrder() {
        // Enter menu items from the customers
    }

    public void serve() {
        // Change status of the order
        // The customer can send the food back possibly because the food was too cold,
        // the order was wrong and needs to be adjusted, etc.
    }

    public void checkout() {
        // Print the bill
    }
}
