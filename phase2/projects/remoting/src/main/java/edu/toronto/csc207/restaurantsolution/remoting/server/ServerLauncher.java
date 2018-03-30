package edu.toronto.csc207.restaurantsolution.remoting.server;

import edu.toronto.csc207.restaurantsolution.remoting.DataManager;

import java.rmi.RemoteException;
import java.util.logging.*;

/**
 * Launches a centralized RMI server for distributed client synchronization.
 */
public final class ServerLauncher {
  public static void main(String[] args) {
    try {
      DataManager server = new DataServer();
      RemoteObjectBinder binder = new RemoteObjectBinder(ServerInfo.port);
      binder.bind(ServerInfo.name, server);
    } catch (RemoteException e) {
      // This exception will be handled/logged in DataServer.
      // We have no control over the network, so we cannot handle such exceptions; the server
      // ... must be restarted.
      // This is the philosophy used in the client as well, though it will try to throw remote
      // ... exceptions back to the data server.
      throw new RuntimeException(e);
    }
  }
}
