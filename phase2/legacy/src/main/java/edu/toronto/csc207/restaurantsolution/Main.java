package edu.toronto.csc207.restaurantsolution;

import edu.toronto.csc207.restaurantsolution.legacy.framework.services.ServiceContainer;
import edu.toronto.csc207.restaurantsolution.legacy.services.EventDriverService;
import edu.toronto.csc207.restaurantsolution.legacy.services.KitchenFactoryService;
import edu.toronto.csc207.restaurantsolution.legacy.services.LoggingOutputService;

import java.io.IOException;

/**
 * Main class of this restaurant program.
 */
public class Main {

  /**
   * Main method of this restaurant program.
   *
   * @param args an array of string
   * @throws IOException halts the program
   */
  public static void main(String[] args) throws IOException {
    ServiceContainer container = new ServiceContainer();

    // Getting an instance also creates one from the container.
    container.getInstance(LoggingOutputService.class);

    KitchenFactoryService kitchen = container.getInstance(KitchenFactoryService.class);

    // Creating servers and chefs registers event handlers.
    kitchen.createServer("Bob", 15);
    kitchen.createChef("Joe");

    EventDriverService eventDriver = container.getInstance(EventDriverService.class);
    eventDriver.run();
  }
}
