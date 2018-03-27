package edu.toronto.csc207.restaurantsolution.remoting.server;

import com.sun.security.ntlm.Server;
import edu.toronto.csc207.restaurantsolution.remoting.DataManager;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.*;

/**
 * Launches a centralized RMI server for distributed client synchronization.
 */
public final class ServerLauncher {
  private Logger logger;

  public ServerLauncher(){
    logger = Logger.getLogger("Server Launcher");
  }

  public static void main(String[] args) {
    try {
      DataManager server = new DataServer();
      RemoteObjectBinder binder = new RemoteObjectBinder(ServerInfo.port);
      binder.bind(ServerInfo.name, server);
    } catch (RemoteException e) {
      // TODO: Handle this more effectively
      e.printStackTrace();
    }
  }
}
