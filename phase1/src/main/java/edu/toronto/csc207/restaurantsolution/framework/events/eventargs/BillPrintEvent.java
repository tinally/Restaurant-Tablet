package edu.toronto.csc207.restaurantsolution.framework.events.eventargs;

import edu.toronto.csc207.restaurantsolution.framework.events.EventArgs;

/**
 * An event to trigger a bill print.
 */
public class BillPrintEvent extends EventArgs<BillPrintEvent> {

  /**
   * The table number of the bill to print.
   */
  private Integer tableNumber;

  public BillPrintEvent() {
    super(BillPrintEvent.class);
  }

  public Integer getTableNumber() {
    return tableNumber;
  }

  public void setTableNumber(Integer tableNumber) {
    this.tableNumber = tableNumber;
  }
}

