package edu.toronto.csc207.restaurantsolution.remoting.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public final class InfoLogger {
  private Logger logger;
  private PrintWriter fileWriter;

  public InfoLogger(String loggerName) {
    logger = Logger.getLogger(loggerName);
    try {
      logger.setUseParentHandlers(false);
      FileHandler fh = new FileHandler("log.txt", true);
      SimpleFormatter sf = new SimpleFormatter();
      fh.setFormatter(sf);
      logger.addHandler(fh);
    } catch(IOException e) {
      throw new RuntimeException(e);
    }
  }

  public void printError(String error) {
    logger.log(Level.SEVERE, error);
  }

  public void printInfo(String info) {
    logger.log(Level.INFO, info);
  }

  public void close() {

  }
}
