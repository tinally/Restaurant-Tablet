package edu.toronto.csc207.restaurantsolution.remoting.server;

import edu.toronto.csc207.restaurantsolution.remoting.client.RemoteListener;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * A remote interface to facilitate the use of data update listeners over a network in the
 * distributed RMI application.
 *
 * <p>An update, as used called remote listeners, occurs on the change of any data within this
 * server; remote listeners are informed of this update in order to respond accordingly.
 */
public interface UpdateServer extends Remote {
  /**
   * Registers a remote listener that will be notified of data updates.
   *
   * @param listener the remote listener that will handle updates.
   */
  void registerListener(RemoteListener listener) throws RemoteException;

  void updateListeners() throws RemoteException;
}
