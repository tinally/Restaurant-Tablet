package edu.toronto.csc207.restaurantsolution.framework.events.eventargs;

import edu.toronto.csc207.restaurantsolution.framework.events.EventArgs;
import edu.toronto.csc207.restaurantsolution.model.OrderStatus;

public class OrderChangedEvent extends EventArgs<OrderChangedEvent> {
  private int orderNumber;
  private OrderStatus newStatus;
  private String sender;

  public OrderChangedEvent() {
    super(OrderChangedEvent.class);
    this.setSender("System");
  }
  public OrderChangedEvent(int orderNumber, OrderStatus newStatus) {
    super(OrderChangedEvent.class);
    this.orderNumber = orderNumber;
    this.newStatus = newStatus;
    this.setSender("System");
  }

  public int getOrderNumber() {
    return orderNumber;
  }

  public OrderStatus getNewStatus() {
    return newStatus;
  }

  public String getSender() {
    return sender;
  }

  public void setSender(String sender) {
    this.sender = sender;
  }
}
