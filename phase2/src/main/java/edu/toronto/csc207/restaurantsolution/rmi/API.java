package edu.toronto.csc207.restaurantsolution.rmi;

import edu.toronto.csc207.restaurantsolution.rmi.client.UpdateListener;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * An interface between client and server.
 */
public interface API extends Remote {
  String name = "Master";

  /**
   * Registers a remote client to which updates will be pushed.
   *
   * @param client the remote client that will handle pushed updates.
   */
  void registerClient(UpdateListener client) throws RemoteException;
}
