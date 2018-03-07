package services;

import events.EventEmitter;
import events.newevents.OrderChangedEvent;
import events.newevents.OrderCreatedEvent;
import kitchen.Order;
import services.framework.Service;
import services.framework.ServiceConstructor;

/**
 * A {@link Service} that outputs the results of events to the console.
 */
public class ConsoleOutputService extends Service {

  /**
   * Service Constructor to instantiate a {@link ConsoleOutputService} from
   *  {@link services.framework.ServiceContainer}.
   * @param emitter {@link EventEmitter} dependency intended
   *        to be resolved by the {@link services.framework.ServiceContainer}
   */
  @ServiceConstructor
  public ConsoleOutputService(EventEmitter emitter) {
    emitter.registerEventHandler(this::printEvent, OrderChangedEvent.class);
    emitter.registerEventHandler(this::printEvent, OrderCreatedEvent.class);
  }

  /**
   * Print output when an order is created
   * @param e The event that occurred.
   */
  private void printEvent (OrderCreatedEvent e) {
    Order order = e.getNewOrder();
    System.out.println("Order # "
        + order.getOrderNumber()
        + " was CREATED by "
        + order.getServerName()
        + " for a "
        + order.getMenuItem().getName());
  }

  /**
   * Print output when an order is changed.
   * @param e The event that occurred.
   */
  private void printEvent (OrderChangedEvent e) {
    System.out.println("Order # " + e.getOrderNumber() + " was " + e.getNewStatus() + " by " + e.getSender());
  }
}
