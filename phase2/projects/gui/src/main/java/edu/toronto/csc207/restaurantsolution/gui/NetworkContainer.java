package edu.toronto.csc207.restaurantsolution.gui;

import edu.toronto.csc207.restaurantsolution.remoting.DataManager;
import edu.toronto.csc207.restaurantsolution.remoting.DataService;

public class NetworkContainer {
  public static DataService dataService;
  public static DataManager dataManager;

  // TODO: Remove this...
  public static void initManager() {
    try {
      dataService = new DataService("localhost");
      dataManager = dataService.getDataManager();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
