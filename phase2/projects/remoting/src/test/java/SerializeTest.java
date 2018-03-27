import edu.toronto.csc207.restaurantsolution.remoting.DataManager;
import edu.toronto.csc207.restaurantsolution.remoting.DataService;
import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;

import static org.junit.jupiter.api.Assertions.*;

class SerializeTest {
  @Test
  void testGetSerializedAccount() throws RemoteException {
    DataService service = new DataService("localhost");
    DataManager manager = service.getDataManager();
    assertEquals(manager.getUserAccount("admin").getUsername(), "admin");
  }
}
