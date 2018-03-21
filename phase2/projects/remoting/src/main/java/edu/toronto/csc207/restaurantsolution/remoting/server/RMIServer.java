package edu.toronto.csc207.restaurantsolution.remoting.server;

import edu.toronto.csc207.restaurantsolution.remoting.API;

import java.rmi.AlreadyBoundException;
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
      // TODO: Dependency injection
      Remote stub = UnicastRemoteObject.exportObject(api, port);
      Registry registry = LocateRegistry.createRegistry(port);
      registry.bind(API.name, stub);
    } catch (RemoteException | AlreadyBoundException e) {
      // TODO: Handle this properly
      System.out.println();
    }
  }
}
