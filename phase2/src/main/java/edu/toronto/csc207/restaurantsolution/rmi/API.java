package edu.toronto.csc207.restaurantsolution.rmi;

import edu.toronto.csc207.restaurantsolution.rmi.client.UpdateListener;

import java.rmi.Remote;

/**
 * An interface between client and server.
 */
public interface API extends Remote {
  static final String name = "Master";

  /**
   * Registers a remote client to which updates will be pushed.
   *
   * @param client the remote client that will handle pushed updates.
   */
  void registerClient(UpdateListener client);

  /**
   * Notifies all registered clients of a change in data that must be pulled.
   */
  void notifyUpdate();
}
