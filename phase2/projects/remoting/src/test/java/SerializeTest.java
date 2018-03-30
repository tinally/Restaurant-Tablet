import edu.toronto.csc207.restaurantsolution.remoting.DataManager;
import edu.toronto.csc207.restaurantsolution.remoting.DataService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SerializeTest {
  @Test
  void testGetSerializedAccount() throws Exception {
    DataService service = new DataService("localhost");
    DataManager manager = service.getDataManager();
    assertEquals(manager.getUserAccount("admin").getUsername(), "admin");
  }
}
