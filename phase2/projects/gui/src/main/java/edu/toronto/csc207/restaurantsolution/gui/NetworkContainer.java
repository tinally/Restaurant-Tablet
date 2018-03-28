package edu.toronto.csc207.restaurantsolution.gui;

import edu.toronto.csc207.restaurantsolution.remoting.DataManager;
import edu.toronto.csc207.restaurantsolution.remoting.DataService;

import java.io.*;

public class NetworkContainer {
  public static DataService dataService;
  public static DataManager dataManager;

  // TODO: Remove this...
  public static void initManager() throws Exception {
    // TODO: DataService should not throw "Exception"
    if (dataService == null) {
      dataService = new DataService("localhost");
      dataManager = dataService.getDataManager();
    }
  }

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
    } catch (Exception e) {
      return false;
    }
  }
}
