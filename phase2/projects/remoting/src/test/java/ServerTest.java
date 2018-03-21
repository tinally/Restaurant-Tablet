import edu.toronto.csc207.restaurantsolution.remoting.DataManager;
import edu.toronto.csc207.restaurantsolution.remoting.server.ServerInfo;
import edu.toronto.csc207.restaurantsolution.remoting.server.RemoteObjectBinder;
import edu.toronto.csc207.restaurantsolution.remoting.server.DataServer;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ServerTest {
  @Test
  void testServerStartup() {
    DataManager server = new DataServer();
    RemoteObjectBinder binder = new RemoteObjectBinder(ServerInfo.port);
    binder.bind(ServerInfo.name, server);

    // No exceptions is a good thing, too!
    assertEquals(true, binder.unbind(ServerInfo.name));
  }
}
