package services;

import kitchen.Order;
import kitchen.OrderStatus;
import payment.Bill;
import restaurant.Table;
import services.framework.Service;
import services.framework.ServiceConstructor;

import java.util.ArrayList;

/**
 * A BillPrinterService prints bills according to user-defined specification.
 */
public class BillPrinterService extends Service {

  private final OrderManagerService orderManagerService;

  /**
   * Constructs a new BillPrinterService.
   */
  @ServiceConstructor
  public BillPrinterService(OrderManagerService orderManagerService) {
    this.orderManagerService = orderManagerService;
  }

  /**
   * Returns a string printing of a bill.
   *
   * @param bill the bill to be printed.
   * @return a string representation of the specified bill.
   */
  public String printBill(Table table) {
    StringBuilder accumulator = new StringBuilder();
    // Format: Bill for Table #TableNumber
    accumulator.append("Bill for Table #");
    accumulator.append(table.getTableNumber());
    accumulator.append(System.lineSeparator());
    // Format: Item:Price
    for (Order order : this.orderManagerService.getOrdersForTableNumber(table.getTableNumber())) {
      if (order.getStatus() != OrderStatus.BILLABLE) continue;
      accumulator.append('\t');
      accumulator.append(order.toString());
      accumulator.append(":\t$");
      accumulator.append(order.getMenuItem().getPrice());
      accumulator.append(System.lineSeparator());
    }
    return accumulator.toString();
  }

//  /**
//   * Returns a string printing of a collection of bills.
//   *
//   * @param bills the collection of bills to be printed.
//   * @return a string representation of the specified bills.
//   */
//  public String printBills(ArrayList<Table> tables) {
//    StringBuilder accumulator = new StringBuilder();
//    for (Table table : tables) {
//      accumulator.append(printBill(table));
//    }
//
//    return accumulator.toString();
//  }

}
