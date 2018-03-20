package edu.toronto.csc207.restaurantsolution.rmi.client;

import edu.toronto.csc207.restaurantsolution.rmi.API;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

// TODO: Extend service / move to package?
public class RMIClient {
  private API api;

  public RMIClient(String host) {
    try {
      Registry registry = LocateRegistry.getRegistry(host);
      api = (API) registry.lookup(API.name);
    } catch (RemoteException | NotBoundException e) {
      // TODO: Handle this properly
      System.out.println();
    }
  }

  public API getAPI() {
    return api;
  }
}
