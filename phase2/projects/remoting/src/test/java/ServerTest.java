import edu.toronto.csc207.restaurantsolution.remoting.DataServer;
import edu.toronto.csc207.restaurantsolution.remoting.server.RemoteObjectBinder;
import edu.toronto.csc207.restaurantsolution.remoting.server.Server;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ServerTest {
  @Test
  void testServerStartup() {
    DataServer server = new Server();
    RemoteObjectBinder binder = new RemoteObjectBinder(DataServer.port);
    binder.bind(DataServer.name, server);

    // No exceptions is a good thing, too!
    assertEquals(true, binder.unbind(DataServer.name));
  }
}
