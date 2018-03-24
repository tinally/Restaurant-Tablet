package edu.toronto.csc207.restaurantsolution.remoting;

import edu.toronto.csc207.restaurantsolution.remoting.client.Client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * A factory for clients to retrieve a remote data manager.
 * This class should be instantiated only once for each client.
 */
public final class DataManagerFactory {
  private Client client;

  public DataManagerFactory() {
    try {
      client = new Client("localhost");
    } catch (RemoteException | NotBoundException e) {

    }
  }

  public DataManager getDataManager() {
    return (DataManager) client.getRemoteInterface();
  }
}
