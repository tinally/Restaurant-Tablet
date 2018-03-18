package edu.toronto.csc207.restaurantsolution.services;

import edu.toronto.csc207.restaurantsolution.framework.services.Service;
import edu.toronto.csc207.restaurantsolution.framework.services.ServiceConstructor;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIClient extends Service {
  private Registry registry;

  @ServiceConstructor
  public RMIClient() {
    try {
      registry = LocateRegistry.getRegistry("localhost");
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
