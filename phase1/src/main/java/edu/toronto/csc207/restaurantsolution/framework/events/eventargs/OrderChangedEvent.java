package edu.toronto.csc207.restaurantsolution.framework.events.eventargs;

import edu.toronto.csc207.restaurantsolution.framework.events.EventArgs;
import edu.toronto.csc207.restaurantsolution.model.OrderStatus;

/**
 * OrderChangedEvent represents the event for changing the status of the order.
 */
public class OrderChangedEvent extends EventArgs<OrderChangedEvent> {
  /**
   * The order number of the Order.
   */
  private int orderNumber;
  /**
   * The new status of the Order.
   */
  private OrderStatus newStatus;
  /**
   * The sender of this OrderChangedEvent.
   */
  private String sender;

  /**
   * Default constructor for OrderChangedEvent.
   */
  public OrderChangedEvent() {
    super(OrderChangedEvent.class);
    this.setSender("System");
  }

  /**
   * Class constructor specifying the orderNumber and newStatus.
   *
   * @param orderNumber the order number of the Order
   * @param newStatus   the new status of the Order
   */
  public OrderChangedEvent(int orderNumber, OrderStatus newStatus) {
    super(OrderChangedEvent.class);
    this.orderNumber = orderNumber;
    this.newStatus = newStatus;
    this.setSender("System");
  }

  /**
   * Returns the order number of this Order.
   *
   * @return the order number of this Order
   */
  public int getOrderNumber() {
    return orderNumber;
  }

  /**
   * Returns the new status of this Order.
   *
   * @return the new status of this Order
   */
  public OrderStatus getNewStatus() {
    return newStatus;
  }

  /**
   * Returns the sender of this OrderChangedEvent.
   *
   * @return the sender of this OrderChangedEvent
   */
  public String getSender() {
    return sender;
  }

  /**
   * Sets the sender of this OrderChangedEvent.
   *
   * @param sender the sender of this OrderChangedEvent
   */
  public void setSender(String sender) {
    this.sender = sender;
  }
}
