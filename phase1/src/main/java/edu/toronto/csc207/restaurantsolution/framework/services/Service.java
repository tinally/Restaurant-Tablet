package edu.toronto.csc207.restaurantsolution.framework.services;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * A service to be instantiated by a {@link ServiceContainer}
 * must extend this class.
 */
public abstract class Service {
  protected final Logger logger;

   // Set up the "Kitchen" logger.
   static {
     Logger logger = Logger.getLogger("Kitchen");

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

  public Service() {
    this.logger = Logger.getLogger("Kitchen");

  }
}
