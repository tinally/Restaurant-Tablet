package services;

import events.EventEmitter;
import events.newevents.OrderChangedEvent;
import events.newevents.OrderCreatedEvent;
import kitchen.Order;
import services.framework.Service;
import services.framework.ServiceConstructor;

public class ConsoleOutputService extends Service {

  @ServiceConstructor
  public ConsoleOutputService(EventEmitter em) {
    em.registerEventHandler(this::printEvent, OrderChangedEvent.class);
    em.registerEventHandler(this::printEvent, OrderCreatedEvent.class);
  }

  private void printEvent (OrderCreatedEvent e) {
    Order order = e.getNewOrder();
    System.out.println("Order # "
        + order.getOrderNumber()
        + " was CREATED by "
        + order.getServerName()
        + " for a "
        + order.getMenuItem().getName());
  }

  private void printEvent (OrderChangedEvent e) {
    System.out.println("Order # " + e.getOrderNumber() + " was " + e.getNewStatus() + " by " + e.getSender());
  }
}
