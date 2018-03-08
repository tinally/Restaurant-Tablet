package edu.toronto.csc207.restaurantsolution.framework.events.eventargs;

import edu.toronto.csc207.restaurantsolution.framework.events.EventArgs;

/**
<<<<<<< HEAD:phase1/src/events/newevents/BillPrintEvent.java
 * BillPrintEvent represents the event for printing the bill.
 */
public class BillPrintEvent extends EventArgs<BillPrintEvent> {

    /**
     * Table number of the bill to be printed.
     */
    private Integer tableNumber;
=======
 * An event to trigger a bill print.
 */
public class BillPrintEvent extends EventArgs<BillPrintEvent> {

  /**
   * The table number of the bill to print.
   */
  private Integer tableNumber;
>>>>>>> 6c2ba67e6d43c990817f7455d710287af5146462:phase1/src/main/java/edu/toronto/csc207/restaurantsolution/framework/events/eventargs/BillPrintEvent.java

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

