package edu.toronto.csc207.restaurantsolution.remoting;

import edu.toronto.csc207.restaurantsolution.remoting.client.UpdateListener;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * A remote data manager to be used by clients in updating and distributing
 * data.
 */
public interface DataManager extends Remote {
  /**
   * Registers a remote update listener that will be notified of data updates.
   *
   * @param listener the remote listener that will handle updates.
   */
  void registerListener(UpdateListener listener) throws RemoteException;
}
