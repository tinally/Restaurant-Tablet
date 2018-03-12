package edu.toronto.csc207.restaurantsolution.services;

import edu.toronto.csc207.restaurantsolution.framework.events.EventEmitter;
import edu.toronto.csc207.restaurantsolution.framework.services.Service;
import edu.toronto.csc207.restaurantsolution.framework.services.ServiceConstructor;
import edu.toronto.csc207.restaurantsolution.model.Chef;
import edu.toronto.csc207.restaurantsolution.model.Server;

/**
 * A {@link Service} to create instances of {@link Server} and
 * {@link Chef}, injecting its required dependencies.
 */
public class KitchenFactoryService extends Service {
  private final EventEmitter eventEmitter;
  private final InventoryFactoryService inventoryFactoryService;
  private final OrderManagerService orderManagerService;
  private final PaymentService paymentService;

  @ServiceConstructor
  public KitchenFactoryService(EventEmitter eventEmitter,
                               InventoryFactoryService inventoryFactoryService,
                               OrderManagerService orderManagerService,
                               PaymentService paymentService) {
    this.eventEmitter = eventEmitter;
    this.inventoryFactoryService = inventoryFactoryService;
    this.orderManagerService = orderManagerService;
    this.paymentService = paymentService;
  }

  /**
   * Create a {@link Server} with the given name and table.
   *
   * @param name The name of the server.
   * @return The newly instantiated Server.
   */
  public Server createServer(String name, int tableNumber) {
    return new Server(eventEmitter,
        name,
        tableNumber,
        inventoryFactoryService.getInventory(),
        orderManagerService,
        paymentService);

  }

  /**
   * Create a {@link Chef} with the given name.
   *
   * @param name The name of the Chef.
   * @return The newly instantiated Chef.
   */
  public Chef createChef(String name) {
    return new Chef(name, eventEmitter, inventoryFactoryService.getInventory(), orderManagerService);
  }
}
