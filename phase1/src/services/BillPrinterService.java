package services;

import kitchen.Order;
import payment.Bill;
import services.framework.Service;
import services.framework.ServiceConstructor;

import java.util.ArrayList;

/**
 * A BillPrinterService prints bills according to user-defined specification.
 */
public class BillPrinterService extends Service {

  /**
   * Constructs a new BillPrinterService.
   */
  @ServiceConstructor
  public BillPrinterService() {
  }

  /**
   * Returns a string printing of a bill.
   *
   * @param bill the bill to be printed.
   * @return a string representation of the specified bill.
   */
  public String printBill(Bill bill) {
    StringBuilder accumulator = new StringBuilder();
    // Format: Bill for Table #TableNumber
    accumulator.append("Bill for Table #");
    accumulator.append(bill.getTable().getTableNumber());
    accumulator.append(System.lineSeparator());
    // Format: Item:Price
    for (Order order : bill.getOrders()) {
      accumulator.append('\t');
      accumulator.append(order.toString());
      accumulator.append(":\t$");
      accumulator.append(order.getMenuItem().getPrice());
      accumulator.append(System.lineSeparator());
    }
    return accumulator.toString();
  }

  /**
   * Returns a string printing of a collection of bills.
   *
   * @param bills the collection of bills to be printed.
   * @return a string representation of the specified bills.
   */
  public String printBills(ArrayList<Bill> bills) {
    StringBuilder accumulator = new StringBuilder();
    for (Bill bill : bills) {
      accumulator.append(printBill(bill));
    }

    return accumulator.toString();
  }

  /**
   * Returns a string printing of a collection of bills.
   *
   * @param bills the collection of bills to be printed.
   * @return a string representation of the specified bills.
   */
  public String printBills(Bill[] bills) {
    StringBuilder accumulator = new StringBuilder();
    for (Bill bill : bills) {
      accumulator.append(printBill(bill));
    }

    return accumulator.toString();
  }

}
