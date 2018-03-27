package edu.toronto.csc207.restaurantsolution.remoting;

import edu.toronto.csc207.restaurantsolution.remoting.client.DataClient;
import edu.toronto.csc207.restaurantsolution.remoting.server.ServerInfo;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.*;

/**
 * Manages interaction between local objects and the central server of the distributed RMI
 * application.
 */
public final class DataService {
  private DataClient dataClient;
  private DataManager dataManager;
  private Logger logger;

  /** Constructs a new data service. */
  public DataService(String host) {
    logger = Logger.getLogger("Data Service");
    try {
      Registry registry = LocateRegistry.getRegistry(host, ServerInfo.port);
      dataClient = new DataClient(registry);
      dataManager = (DataManager) dataClient.getRemoteInterface();
    } catch (RemoteException | NotBoundException e) {
      // TODO: Handle this
      e.printStackTrace();
    }
  }

  /**
   * Gets the data remote data manager for local retrieval and pushing of data to the server.
   *
   * @return the remote data manager.
   */
  public DataManager getDataManager() {
    return dataManager;
  }

  /**
   * Registers a local data listener that will handle data updates.
   *
   * @param listener the data listener that will handle updates.
   */
  public void registerListener(DataListener listener) {
    dataClient.registerListener(listener);
  }
}
