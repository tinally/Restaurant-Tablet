package edu.toronto.csc207.restaurantsolution.remoting.server;

import edu.toronto.csc207.restaurantsolution.remoting.client.RemoteListener;

import java.rmi.RemoteException;

public interface UpdateServer {
  /**
   * Registers a remote listener that will be notified of data updates.
   *
   * @param listener the remote listener that will handle updates.
   */
  void registerListener(RemoteListener listener) throws RemoteException;
}
