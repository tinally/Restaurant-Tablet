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
    ResourceResolverService rrs = container.getInstance(ResourceResolverService.class);
    YamlDeserializerService yds = container.getInstance(YamlDeserializerService.class);
    BillPrinterService bps = container.getInstance(BillPrinterService.class);

    EventEmitter em = container.getInstance(EventEmitter.class);
    OrderManagerService om = container.getInstance(OrderManagerService.class);
    PaymentManagerService pm = container.getInstance(PaymentManagerService.class);
    Inventory im = new Inventory(em);

    ObjectMapper mapper = yds.getMapper();
    List<EventArgs> events = mapper.readValue(rrs.getResource("events.yml"),
        mapper.getTypeFactory().constructCollectionType(List.class, EventArgs.class));
    Server s = new Server(em, bps, "Server Bob", new Table(15, 10), im, om, pm);
    Chef c = new Chef("Joe", em, im, om);
    for (EventArgs e : events) {
      em.onEvent(e);
    }


  }
}
