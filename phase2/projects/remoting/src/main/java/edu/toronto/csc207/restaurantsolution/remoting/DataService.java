package edu.toronto.csc207.restaurantsolution.remoting;

import edu.toronto.csc207.restaurantsolution.remoting.client.DataClient;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * A factory for clients to retrieve a remote data manager.
 * This class should be instantiated only once for each dataClient.
 */
// TODO: Redo documentation for all RMI-related classes
public final class DataService {
  private DataClient dataClient;
  private DataManager dataManager;

  public DataService() {
    try {
      dataClient = new DataClient("localhost");
      dataManager = (DataManager) dataClient.getRemoteInterface();
      dataManager.registerListener(dataClient);
    } catch (RemoteException | NotBoundException e) {

    }
  }

  public DataManager getDataManager() {
    return dataManager;
  }

  public void addDataListener(DataListener listener) {
    dataClient.registerListener(listener);
  }
}
