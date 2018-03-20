package edu.toronto.csc207.restaurantsolution.rmi.client;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

// Client concept:
// Server will have references to the services needed to change each node's
// ... local data models. These models will potentially push events to other
// ... models.
// Any remote service call should cause an update to the active UI following
// ... remote data refresh.

// TODO: Extend service / move to package?
public class RMIClient {
  private Registry registry;

  public RMIClient(String host) {
    try {
      registry = LocateRegistry.getRegistry(host);
    } catch (RemoteException e) {
      // TODO: Handle this properly
      System.out.println();
    }
  }

  public Remote getObject(Remote obj) {
    try {
      return registry.lookup(obj.getClass().getSimpleName());
    } catch (Exception e) {
      // TODO: Catch all exceptions and handle this properly
      return null;
    }
  }
}
