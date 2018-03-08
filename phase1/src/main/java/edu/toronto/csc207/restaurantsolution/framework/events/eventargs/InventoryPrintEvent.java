package edu.toronto.csc207.restaurantsolution.framework.events.eventargs;

import edu.toronto.csc207.restaurantsolution.framework.events.EventArgs;

public class InventoryPrintEvent extends EventArgs<InventoryPrintEvent> {
  public InventoryPrintEvent() {
    super(InventoryPrintEvent.class);
  }
}

