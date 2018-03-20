package edu.toronto.csc207.restaurantsolution.rmi.server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

// Server concept:
// The entire purpose of a server is to propagate full data refreshes to clients
// ... while keeping a persistent record of data models.
// The "server" should be distinguished from the "server node"; i.e., the server
// ... node is the system on the network which is running the "server", but the
// ... server node also contains a client that is updated just like all remote
// ... clients.

// TODO: Extend service / move to package?
public class RMIServer {
  private Registry registry;

  // TODO: Convert to service? Ensure this can only be instantiated at most once

  /**
   * Constructs a new RMI server.
   */
  public RMIServer() {
    try {
      registry = LocateRegistry.createRegistry(1099);
    } catch (RemoteException e) {
      // TODO: Handle this properly
      System.out.println();
    }
  }

  /**
   * Registers a remote object for use by clients to the RMI proxy.
   *
   * @param remoteInterface the object to be bound to the proxy.
   */
  public void register(Remote remoteInterface) {
    try {
      Remote stub = UnicastRemoteObject.exportObject(remoteInterface,
          1099);
      registry.rebind(remoteInterface.getClass().getSimpleName(), stub);
    } catch (RemoteException e) {
      // TODO: Handle this properly
      System.out.println();
    }
  }
}
