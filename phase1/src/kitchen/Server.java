package kitchen;

import events.EventEmitter;
import events.newevents.BillPrintEvent;
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
        serve();
        checkout();
    }

    /**
     * Updates the ingredient when an OrderCompleteEvent is filed.
     *
     * @param event the event called after an order is completed by the Chef
     */
    private void updateIngredient(OrderChangedEvent event) {
        if (event.getNewStatus() != OrderStatus.FILLED) return;
        Order order = orderManager.getOrder(event.getOrderNumber());
        if (order == null) return;

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
        }
    }

    public void addOrder(OrderChangedEvent event) {
        if (event.getNewStatus() == OrderStatus.CREATED) {

        }

    }

    private void serve() {
        // Mark as Billable
        // TODO: add the price onto the bill
        emitter.registerEventHandler(e -> {
            if (e.getNewStatus() == OrderStatus.DELIVERED) {
                orderManager.notifyOrderStatusChanged(e.getOrderNumber(), OrderStatus.BILLABLE, this.name);
            }
        }, OrderChangedEvent.class);
    }

    private void checkout() {
        // Print the bill
        // todo: do this properly with the table?
        emitter.registerEventHandler(e -> {
            if (e.getTableNumber() == this.table.getTableNumber()) {
                logger.info(printer.printBill(new Table(e.getTableNumber(), 1)));
            }
        }, BillPrintEvent.class);
    }
}
