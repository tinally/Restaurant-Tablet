package edu.toronto.csc207.restaurantsolution.services;

import edu.toronto.csc207.restaurantsolution.framework.services.ServiceConstructor;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RMIServer {
  private Registry registry;

  @ServiceConstructor
  RMIServer() {
    try {
      registry = LocateRegistry.createRegistry(1099);
    } catch (RemoteException e) {
      // TODO: Handle this properly
      System.out.println();
    }
  }

  public void registerClass(Remote remoteInterface) {
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
