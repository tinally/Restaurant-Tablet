package edu.toronto.csc207.restaurantsolution.remoting.client;

import edu.toronto.csc207.restaurantsolution.remoting.server.ServerInfo;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * A network client of a remote server.
 */
public final class Client {
  private Remote remoteInterface;

  // TODO: Subclass to get specific class that returns remoteInterface
  // TODO: ... as DataManager

  public Client(String host) {
    try {
      Registry registry = LocateRegistry.getRegistry(host, ServerInfo.port);
      remoteInterface = registry.lookup(ServerInfo.name);
    } catch (RemoteException | NotBoundException e) {

    }
  }

  public Remote getRemoteInterface() {
    return remoteInterface;
  }
}
