package services;

import events.EventEmitter;
import events.newevents.*;
import kitchen.Order;
import services.framework.Service;
import services.framework.ServiceConstructor;

/**
 * A {@link Service} that outputs the results of events to the console and event.txt
 */
public class LoggingOutputService extends Service {

  /**
   * Service Constructor to instantiate a {@link LoggingOutputService} from
   *  {@link services.framework.ServiceContainer}.
   * @param emitter {@link EventEmitter} dependency intended
   *        to be resolved by the {@link services.framework.ServiceContainer}
   */
  @ServiceConstructor
  public LoggingOutputService(EventEmitter emitter, PaymentService paymentService) {

    emitter.registerEventHandler(this::printEvent, OrderChangedEvent.class);
    emitter.registerEventHandler(this::printEvent, OrderCreatedEvent.class);
    emitter.registerEventHandler(this::printEvent, IngredientReorderEvent.class);
    emitter.registerEventHandler(this::printEvent, IngredientRestockEvent.class);
    // Print the bill
    // todo: do this properly with the table?

  }

  private void printEvent(IngredientReorderEvent e) {
    logger.info(e.getIngredient() + " needs to reorder!");
  }

  private void printEvent(IngredientRestockEvent e) {
    logger.info(e.getIngredient() + " was restocked by " + e.getIngredient().getReorderAmount());
  }
  /**
   * Print output when an order is created
   * @param e The event that occurred.
   */
  private void printEvent (OrderCreatedEvent e) {
    Order order = e.getNewOrder();
    logger.info("Order # "
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
    logger.info("Order # " + e.getOrderNumber() + " was " + e.getNewStatus() + " by " + e.getSender());
  }
}
