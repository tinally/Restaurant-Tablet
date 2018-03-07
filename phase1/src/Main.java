import com.fasterxml.jackson.databind.ObjectMapper;
import events.EventArgs;
import events.EventEmitter;
import kitchen.Chef;
import kitchen.Inventory;
import kitchen.Server;
import restaurant.Table;
import services.*;
import services.framework.ServiceContainer;
import services.serialization.YamlDeserializerService;

import java.io.IOException;
import java.util.List;

public class Main {
  public static void main(String[] args) throws IOException {
    ServiceContainer container = new ServiceContainer();
    container.getInstance(ConsoleOutputService.class);
    KitchenFactoryService kitchen = container.getInstance(KitchenFactoryService.class);

    Server bob = kitchen.createServer("Bob", new Table(15, 10));
    Chef joe = kitchen.createChef("Joe");

    EventDriverService eventDriver = container.getInstance(EventDriverService.class);
    eventDriver.run();
  }
}
