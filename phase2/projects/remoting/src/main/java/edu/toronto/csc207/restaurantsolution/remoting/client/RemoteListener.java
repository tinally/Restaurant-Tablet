package edu.toronto.csc207.restaurantsolution.remoting.client;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * A remote interface for listeners in the distributed RMI application that should respond to
 * updates of the data model contained on a server.
 */
public interface RemoteListener extends Remote {
  /**
   * Handles a data update. This method should only be called by a remote subject that has
   * registered this remote listener.
   *
   * @throws RemoteException if a remote error occurs.
   */
  void update() throws RemoteException;
}
