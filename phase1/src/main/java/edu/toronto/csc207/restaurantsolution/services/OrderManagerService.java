package edu.toronto.csc207.restaurantsolution.services;

import edu.toronto.csc207.restaurantsolution.data.MenuItem;
import edu.toronto.csc207.restaurantsolution.framework.events.EventEmitter;
import edu.toronto.csc207.restaurantsolution.framework.events.eventargs.OrderChangedEvent;
import edu.toronto.csc207.restaurantsolution.framework.events.eventargs.OrderCreatedEvent;
import edu.toronto.csc207.restaurantsolution.framework.services.Service;
import edu.toronto.csc207.restaurantsolution.framework.services.ServiceConstructor;
import edu.toronto.csc207.restaurantsolution.framework.services.ServiceContainer;
import edu.toronto.csc207.restaurantsolution.model.Order;
import edu.toronto.csc207.restaurantsolution.model.OrderStatus;

import java.util.*;
import java.util.stream.Collectors;

/**
 * A {@link Service} to manage the creation and retention of {@link Order} instances.
 */
public class OrderManagerService extends Service {
  /**
   * The {@link EventEmitter} that will emit events to respond to.
   */
  private final EventEmitter emitter;

  /**
   * The Map that keeps track of orders.
   */
  private Map<Integer, Order> orders;

  /**
   * ServiceConstructor to instantiate a OrderManagerService.
   *
   * @param emitter {@link EventEmitter} dependency intended
   *                to be resolved by the {@link ServiceContainer}
   * @see ServiceContainer#getInstance(Class)
   */
  @ServiceConstructor
  public OrderManagerService(EventEmitter emitter) {
    this.emitter = emitter;

    // There is a soft assumption that these event handlers
    // are registered before any other class registers any
    // order events.

    emitter.registerEventHandler(this::saveOrder, OrderCreatedEvent.class);
    emitter.registerEventHandler(this::updateOrder, OrderChangedEvent.class);
    this.orders = new HashMap<>();
  }

  /**
   * Updates the corresponding {@link Order} whenever an {@link OrderChangedEvent} happens.
   *
   * @param event The {@link OrderChangedEvent} that occured
   */
  private void updateOrder(OrderChangedEvent event) {
    // Ensure that the order status matches with the event status.
    // May not need this after removing event.txt
    this.getOrder(event.getOrderNumber()).setStatus(event.getNewStatus());
  }

  /**
   * Factory method that creates an {@link Order}
   *
   * @param tableNumber The table number of the order.
   * @param serverName  The name of the server this order was assigned to.
   * @param menuItem    The menu item for this order.
   */
  // TODO: Use in phase 2
  public void createOrder(int tableNumber, String serverName, MenuItem menuItem) {
    Order order = new Order(menuItem, tableNumber, serverName, new Random().nextInt());
    OrderCreatedEvent event = new OrderCreatedEvent();
    event.setNewOrder(order);
    emitter.onEvent(event);
  }

  /**
   * Saves an order to the cache {@link #orders} when an {@link OrderCreatedEvent}
   * occurs. Intended mostly for event.txt tooling to retain orders created from
   * external events (such as event deserialization).
   *
   * @param event The event that occurred.
   */
  private void saveOrder(OrderCreatedEvent event) {
    Order order = event.getNewOrder();
    this.orders.put(order.getOrderNumber(), order);
  }

  /**
   * Gets an {@link Order} from the cache with the given order number.
   *
   * @param orderNumber The order number of the order.
   * @return The order with the given order number, or null otherwise.
   */
  public Order getOrder(int orderNumber) {
    return orders.get(orderNumber);
  }

  /**
   * Gets a read-only view of all the {@link Order} instances
   * currently managed.
   *
   * @return A read-only view of all the {@link Order} instances
   * currently managed.
   */
  // TODO: Use in phase 2
  public Collection<Order> getAllOrders() {
    return Collections.unmodifiableCollection(orders.values());
  }

  /**
   * Gets a read-only view of all the {@link Order} instances
   * with the given table number.
   *
   * @param tableNumber The table number
   * @return All the orders for the given table number.
   */
  // TODO: Use in phase 2
  public List<Order> getOrdersForTableNumber(int tableNumber) {
    return Collections.unmodifiableList(
        orders.values().stream().filter(o -> o.getTableNumber() == tableNumber).collect(Collectors.toList())
    );
  }

  /**
   * Fire an {@link OrderChangedEvent} that notifies listeners that an
   * order was changed.
   *
   * @param orderNumber The order number of the order to update the status of.
   * @param newStatus   The new status to change the order.
   * @param sender      The name of the object that sent the event.
   */
  public void notifyOrderStatusChanged(int orderNumber, OrderStatus newStatus, String sender) {
    OrderChangedEvent event = new OrderChangedEvent(orderNumber, newStatus);
    event.setSender(sender);
    emitter.onEvent(event);
  }
}
