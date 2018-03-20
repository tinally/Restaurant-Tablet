package edu.toronto.csc207.restaurantsolution.rmi.server;

import edu.toronto.csc207.restaurantsolution.rmi.API;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RMIServer {
  // TODO: Convert to service? Ensure this can only be instantiated at most once

  /**
   * Constructs a new RMI server.
   */
  public RMIServer(API api, int port) {
    try {
      Registry registry = LocateRegistry.createRegistry(port);
      Remote stub = UnicastRemoteObject.exportObject(api, port);
      registry.rebind(API.name, stub);
    } catch (RemoteException e) {
      // TODO: Handle this properly
      System.out.println();
    }
  }
}
