package edu.toronto.csc207.restaurantsolution.framework.events.eventargs;

import edu.toronto.csc207.restaurantsolution.framework.events.EventArgs;

/**
 * BillPrintEvent represents the event for printing the bill.
 */
public class BillPrintEvent extends EventArgs<BillPrintEvent> {
  /**
   * The table number of the bill to print.
   */
  private Integer tableNumber;

  /**
   * Default constructor for BillPrintEvent.
   */
  public BillPrintEvent() {
    super(BillPrintEvent.class);
  }

  /**
   * Returns the table number of the bill to be printed.
   *
   * @return the table number of the bill to be printed
   */
  public Integer getTableNumber() {
    return tableNumber;
  }

  /**
   * Sets the table number of this bill.
   *
   * @param tableNumber the table number to be set
   */
  public void setTableNumber(Integer tableNumber) {
    this.tableNumber = tableNumber;
  }
}

