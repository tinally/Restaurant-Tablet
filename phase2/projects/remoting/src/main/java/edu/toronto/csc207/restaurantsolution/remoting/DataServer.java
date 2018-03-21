package edu.toronto.csc207.restaurantsolution.remoting;

import edu.toronto.csc207.restaurantsolution.remoting.client.UpdateListener;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * An interface for a data server to be used by clients.
 */
public interface DataServer extends Remote {
  String name = "Master";
  int port = 1099;

  /**
   * Registers a remote listener that will be notified of data updates.
   *
   * @param listener the remote listener that will handle updates.
   */
  void registerClient(UpdateListener listener) throws RemoteException;
}
