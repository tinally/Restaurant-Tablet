package edu.toronto.csc207.restaurantsolution.remoting.app;

import edu.toronto.csc207.restaurantsolution.remoting.DataManager;
import edu.toronto.csc207.restaurantsolution.remoting.client.Client;

public final class DataManagerFactory {
  private Client client;

  public DataManagerFactory() {
    client = new Client("localhost");
  }

  public DataManager getDataManager() {
    return (DataManager) client.getRemoteInterface();
  }
}
