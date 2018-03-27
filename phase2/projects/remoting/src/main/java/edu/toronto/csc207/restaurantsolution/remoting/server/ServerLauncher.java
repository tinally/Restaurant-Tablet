package edu.toronto.csc207.restaurantsolution.remoting.server;

import edu.toronto.csc207.restaurantsolution.remoting.DataManager;

import java.rmi.RemoteException;
import java.util.logging.*;

/**
 * Launches a centralized RMI server for distributed client synchronization.
 */
public final class ServerLauncher {
  public static void main(String[] args) {
    Logger logger = Logger.getLogger("Server Launcher");
    try {
      DataManager server = new DataServer();
      RemoteObjectBinder binder = new RemoteObjectBinder(ServerInfo.port);
      binder.bind(ServerInfo.name, server);
    } catch (RemoteException e) {
      logger.log(Level.SEVERE, "Error on network", e);
    }
  }
}
