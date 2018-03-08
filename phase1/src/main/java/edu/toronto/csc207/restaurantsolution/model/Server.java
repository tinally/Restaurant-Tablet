package edu.toronto.csc207.restaurantsolution.model;

import edu.toronto.csc207.restaurantsolution.data.Ingredient;
import edu.toronto.csc207.restaurantsolution.data.MenuItem;
import edu.toronto.csc207.restaurantsolution.framework.events.EventEmitter;
import edu.toronto.csc207.restaurantsolution.framework.events.eventargs.BillPrintEvent;
import edu.toronto.csc207.restaurantsolution.framework.events.eventargs.OrderChangedEvent;
import edu.toronto.csc207.restaurantsolution.framework.services.Service;
import edu.toronto.csc207.restaurantsolution.framework.services.ServiceConstructor;
import edu.toronto.csc207.restaurantsolution.services.OrderManagerService;
import edu.toronto.csc207.restaurantsolution.services.PaymentService;

import java.util.Map;

/**
 * Server represents the server of this restaurant.
 */
public class Server extends Service {
  /**
   * Name of the Server.
   */
  private String name;
  /**
   * The table number of the table that the Server is responsible for.
   */
  private int tableNumber;
  /**
   * Handles the events.
   */
  private EventEmitter emitter;
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
  private PaymentService paymentService;

  /**
   * Class constructor specifying the emitter, printer, name, table, inventory,
   * and manager.
   *
   * @param emitter        main event handler
   * @param name           name of the Server
   * @param tableNumber    the table number the Server is responsible for
   * @param inventory      inventory of all ingredients
   * @param orderManager   manager of the orders
   * @param paymentService manager of the payment
   */
  @ServiceConstructor
  public Server(EventEmitter emitter, String name, int tableNumber,
                Inventory inventory,
                OrderManagerService orderManager,
                PaymentService paymentService) {
    this.emitter = emitter;
    this.name = name;
    this.tableNumber = tableNumber;
    this.inventory = inventory;
    this.orderManager = orderManager;
    this.paymentService = paymentService;
    emitter.registerEventHandler(this::updateIngredient,
        OrderChangedEvent.class);
    emitter.registerEventHandler(this::rejectOrderItem,
        OrderChangedEvent.class);
    paymentService.registerTable(tableNumber);
    serve();
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

  /**
   * Adds an order after the order is created.
   *
   * @param event the event called after an order is created
   */
  public void addOrder(OrderChangedEvent event) {
    if (event.getNewStatus() == OrderStatus.CREATED) {
      orderManager.createOrder(tableNumber, name,
          orderManager.getOrder(event.getOrderNumber()).getMenuItem());
    }

  }

  /**
   * Serves an order to the table.
   */
  private void serve() {
    emitter.registerEventHandler(e -> {
      if (e.getNewStatus() == OrderStatus.DELIVERED) {
        paymentService.registerOrder(tableNumber,
            orderManager.getOrder(e.getOrderNumber()));
      }
    }, OrderChangedEvent.class);
  }

  /**
   * Prints out the bill upon the request of checkout from the customers.
   */
  // TODO: Use in phase 2
  private void checkout() {
    BillPrintEvent event = new BillPrintEvent();
    event.setTableNumber(tableNumber);
    emitter.onEvent(event);
  }
}
