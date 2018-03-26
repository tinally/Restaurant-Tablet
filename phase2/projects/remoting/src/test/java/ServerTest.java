import edu.toronto.csc207.restaurantsolution.remoting.DataManager;
import edu.toronto.csc207.restaurantsolution.remoting.server.ServerInfo;
import edu.toronto.csc207.restaurantsolution.remoting.server.RemoteObjectBinder;
import edu.toronto.csc207.restaurantsolution.remoting.server.DataServer;
import org.junit.jupiter.api.Test;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import static org.junit.jupiter.api.Assertions.*;

class ServerTest {
  @Test
  void testServerStartup() throws RemoteException, NotBoundException{
    DataManager server = new DataServer();
    RemoteObjectBinder binder = new RemoteObjectBinder(ServerInfo.port);
    binder.bind(ServerInfo.name, server);
    binder.unbind(ServerInfo.name);
  }
}
