package ui.net;

import edu.toronto.csc207.restaurantsolution.remoting.server.RemoteObjectBinder;
import edu.toronto.csc207.restaurantsolution.remoting.server.ServerInfo;

import java.rmi.RemoteException;

public class TestServerLauncher {
  public static void main(String[] args) throws RemoteException {
    RemoteObjectBinder binder = new RemoteObjectBinder(ServerInfo.port);
    Unifier server = new Server();
    binder.bind(ServerInfo.name, server);
  }
}
