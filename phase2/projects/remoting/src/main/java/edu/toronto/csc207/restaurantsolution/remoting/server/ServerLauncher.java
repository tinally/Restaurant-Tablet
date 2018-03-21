package edu.toronto.csc207.restaurantsolution.remoting.server;

import edu.toronto.csc207.restaurantsolution.remoting.DataServer;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Launches a centralized RMI server for distributed client synchronization.
 */
public class ServerLauncher {
  public static void main(String[] args) {
    DataServer server = new Server();
    RemoteObjectBinder binder = new RemoteObjectBinder(DataServer.port);
    binder.bind(DataServer.name, server);

    binder.unbind(DataServer.name);
  }
}
