package edu.toronto.csc207.restaurantsolution.remoting.server;

import edu.toronto.csc207.restaurantsolution.remoting.DataManager;

/**
 * Launches a centralized RMI server for distributed client synchronization.
 */
public final class ServerLauncher {
  public static void main(String[] args) {
    DataManager server = new DataServer();
    RemoteObjectBinder binder = new RemoteObjectBinder(ServerInfo.port);
    binder.bind(ServerInfo.name, server);

    binder.unbind(ServerInfo.name);
  }
}
