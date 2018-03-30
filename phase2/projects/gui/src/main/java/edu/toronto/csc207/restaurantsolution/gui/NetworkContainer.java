package edu.toronto.csc207.restaurantsolution.gui;

import edu.toronto.csc207.restaurantsolution.remoting.DataManager;
import edu.toronto.csc207.restaurantsolution.remoting.DataService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;

/**
 * Utility for maintaining network connection over multiple threads and configuring network
 * initially.
 */
public class NetworkContainer {
  // Static variables prevent having to pass reference through each consecutive controller
  /** The data service for registering controllers as remote listeners. */
  public static DataService dataService;
  /** The data manager for sending and retrieving data from the data server. */
  public static DataManager dataManager;

  /**
   * Retrieves the hostname for the RMI server from a file and initializes remote data manager.
   *
   * @return true if the network configuration file was successfully read and contained a usable
   * host
   */
  public static boolean getSavedNetwork() {
    try {
      ClassLoader loader = Thread.currentThread().getContextClassLoader();
      InputStream inputStream = loader.getResourceAsStream("network.cfg");
      InputStreamReader streamReader = new InputStreamReader(inputStream);
      BufferedReader reader = new BufferedReader(streamReader);
      String host = reader.readLine();
      dataService = new DataService(host);
      dataManager = dataService.getDataManager();
      return true;
    } catch (NotBoundException | IOException e) {
      return false;
    }
  }
}
