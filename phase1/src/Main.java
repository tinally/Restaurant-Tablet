import kitchen.Chef;
import kitchen.Server;
import restaurant.Table;
import services.*;
import services.framework.ServiceContainer;

import java.io.IOException;

/**
 * Main method of this restaurant program.
 */
public class Main {

    /**
     * Main program of this restaurant.
     *
     * @param args the arguments for this program
     * @throws IOException if program halts
     */
    public static void main(String[] args) throws IOException {
        ServiceContainer container = new ServiceContainer();
        container.getInstance(LoggingOutputService.class);
        KitchenFactoryService kitchen = container.getInstance(KitchenFactoryService.class);

        Server bob = kitchen.createServer("Bob", new Table(15, 10));
        Chef joe = kitchen.createChef("Joe");

        EventDriverService eventDriver = container.getInstance(EventDriverService.class);
        eventDriver.run();
    }
}
