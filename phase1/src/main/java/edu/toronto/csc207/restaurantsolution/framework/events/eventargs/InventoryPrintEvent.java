package edu.toronto.csc207.restaurantsolution.framework.events.eventargs;

import edu.toronto.csc207.restaurantsolution.framework.events.EventArgs;

/**
 * An event to trigger printing of the inventory.
 */
public class InventoryPrintEvent extends EventArgs<InventoryPrintEvent> {
  /**
   * Default constructor for InventoryPrintEvent.
   */
  public InventoryPrintEvent() {
    super(InventoryPrintEvent.class);
  }
}

