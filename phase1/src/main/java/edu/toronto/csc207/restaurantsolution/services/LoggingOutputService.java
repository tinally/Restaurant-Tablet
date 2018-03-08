package edu.toronto.csc207.restaurantsolution.services;

import edu.toronto.csc207.restaurantsolution.framework.events.EventEmitter;
import edu.toronto.csc207.restaurantsolution.framework.events.eventargs.*;
import edu.toronto.csc207.restaurantsolution.framework.services.Service;
import edu.toronto.csc207.restaurantsolution.framework.services.ServiceConstructor;
import edu.toronto.csc207.restaurantsolution.framework.services.ServiceContainer;
import edu.toronto.csc207.restaurantsolution.model.Order;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * A {@link Service} that outputs the results of events to the console and event.txt
 */
public class LoggingOutputService extends Service {
  /**
   * The payment service to be used in printing bills.
   */
  private PaymentService paymentService;
  /**
   * The logger used to print to console/file.
   */
  private Logger logger;

  /**
   * Service Constructor to instantiate a {@link LoggingOutputService} from
   * {@link ServiceContainer}.
   *
   * @param emitter {@link EventEmitter} dependency intended
   *                to be resolved by the {@link ServiceContainer}
   */
  @ServiceConstructor
  public LoggingOutputService(EventEmitter emitter, PaymentService paymentService) {

    this.paymentService = paymentService;

    emitter.registerEventHandler(this::printEvent, OrderChangedEvent.class);
    emitter.registerEventHandler(this::printEvent, OrderCreatedEvent.class);
    emitter.registerEventHandler(this::printEvent, IngredientReorderEvent.class);
    emitter.registerEventHandler(this::printEvent, IngredientRestockEvent.class);
    emitter.registerEventHandler(this::printBill, BillPrintEvent.class);

    logger = Logger.getLogger("Kitchen");

    // Adapted from https://stackoverflow.com/questions/15758685/
    FileHandler fileHandler;
    try {
      fileHandler = new FileHandler("event.txt", true);
      logger.addHandler(fileHandler);
      SimpleFormatter formatter = new SimpleFormatter();
      fileHandler.setFormatter(formatter);

    } catch (SecurityException | IOException ignored) {
    }

  }

  public void printString(String string) {
    logger.info(string);
  }

  /**
   * Prints out e.
   *
   * @param e the IngredientReorderEvent to be printed
   */
  private void printEvent(IngredientReorderEvent e) {
    logger.info(e.getIngredient() + " needs to reorder!");
  }

  /**
   * Prints out e.
   *
   * @param e the IngredientRestockEvent to be printed
   */
  private void printEvent(IngredientRestockEvent e) {
    logger.info(e.getIngredient() + " was restocked by " + e.getIngredient().getReorderAmount());
  }

  /**
   * Print output when an order is created
   *
   * @param e event that occurred.
   */
  private void printEvent(OrderCreatedEvent e) {
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
   *
   * @param e event that occurred.
   */
  private void printEvent(OrderChangedEvent e) {
    logger.info("Order # " + e.getOrderNumber() + " was " + e.getNewStatus() + " by " + e.getSender());
  }

  /**
   * Prints a bill with a specified bill print event.
   *
   * @param e the bill print event that specifies which bill should be printed.
   */
  private void printBill(BillPrintEvent e) {
    logger.info(paymentService.printBill(e.getTableNumber()));
  }
}
