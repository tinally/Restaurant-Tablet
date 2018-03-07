package events.newevents;

import events.EventArgs;

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

