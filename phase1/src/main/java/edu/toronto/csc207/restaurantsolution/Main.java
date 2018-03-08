package edu.toronto.csc207.restaurantsolution;

import edu.toronto.csc207.restaurantsolution.services.EventDriverService;
import edu.toronto.csc207.restaurantsolution.services.KitchenFactoryService;
import edu.toronto.csc207.restaurantsolution.services.LoggingOutputService;
import edu.toronto.csc207.restaurantsolution.model.Chef;
import edu.toronto.csc207.restaurantsolution.model.Server;
import edu.toronto.csc207.restaurantsolution.model.Table;
import edu.toronto.csc207.restaurantsolution.framework.services.ServiceContainer;

import java.io.IOException;

public class Main {
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
