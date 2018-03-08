import edu.toronto.csc207.restaurantsolution.model.Chef;
import edu.toronto.csc207.restaurantsolution.model.Server;
import edu.toronto.csc207.restaurantsolution.model.Table;
import edu.toronto.csc207.restaurantsolution.services.*;
import edu.toronto.csc207.restaurantsolution.framework.services.ServiceContainer;

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

        Server bob = kitchen.createServer("Bob", 15);
        Chef joe = kitchen.createChef("Joe");

        EventDriverService eventDriver = container.getInstance(EventDriverService.class);
        eventDriver.run();
    }
}
