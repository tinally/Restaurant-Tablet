package edu.toronto.csc207.restaurantsolution.remoting.server;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Utility for logging events to a local text file.
 *
 * <p>This class cannot be subclassed and should be instantiated only once per JVM; otherwise,
 * file conflicts may occur.
 */
public final class InfoLogger {
  private Logger logger;

  /**
   * Constructs a new info logger.
   *
   * @param loggerName the name that will be used in the log file.
   * @param logFileName the name that will be used for creating and appending to the log file.
   */
  public InfoLogger(String loggerName, String logFileName) {
    logger = Logger.getLogger(loggerName);
    try {
      logger.setUseParentHandlers(false);
      FileHandler fh = new FileHandler(logFileName, true);
      SimpleFormatter sf = new SimpleFormatter();
      fh.setFormatter(sf);
      logger.addHandler(fh);
    } catch(IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Appends an error (with severe status) to the log file.
   *
   * @param error a description of the error.
   */
  public void printError(String error) {
    logger.log(Level.SEVERE, error);
  }

  /**
   * Appends an information update to the log file.
   *
   * @param info a description of the information.
   */
  public void printInfo(String info) {
    logger.log(Level.INFO, info);
  }
}
