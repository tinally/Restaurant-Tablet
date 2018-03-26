package edu.toronto.csc207.restaurantsolution.remoting.server;

import edu.toronto.csc207.restaurantsolution.remoting.DataManager;
import edu.toronto.csc207.restaurantsolution.remoting.client.RemoteListener;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * The default implementation of DataManager; the backend that handles remote
 * client requests for data and pushes update notifications to all clients with
 * registered listeners.
 */
public final class DataServer implements DataManager {
  private ArrayList<RemoteListener> listeners;

  /**
   * Constructs a new server.
   */
  public DataServer() {
    listeners = new ArrayList<>();
  }

  /**
   * Registers a remote update listener that will be notified of data updates.
   *
   * @param listener the remote listener that will handle updates.
   */
  @Override
  public void registerListener(RemoteListener listener) {
    listeners.add(listener);
  }

  /**
   * Notifies all registered listeners of a data update.
   */
  public void updateListeners() throws RemoteException {
    for (RemoteListener listener : listeners)
      listener.update();
  }
}
