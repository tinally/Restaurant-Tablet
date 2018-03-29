import edu.toronto.csc207.restaurantsolution.remoting.server.InfoLogger;
import org.junit.jupiter.api.Test;

class LoggerTest {
  @Test
  void testLoggerWrite() {
    InfoLogger logger = new InfoLogger("Test", "log.txt");
    logger.printInfo("Info printing");
    logger.printError("Warning printing");
  }
}
