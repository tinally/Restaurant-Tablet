package edu.toronto.csc207.restaurantsolution.framework.events.eventargs;

import edu.toronto.csc207.restaurantsolution.framework.events.EventArgs;

public class BillPrintEvent extends EventArgs<BillPrintEvent> {

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

