package services;

import kitchen.Order;
import kitchen.OrderStatus;
import payment.Bill;
import restaurant.Table;
import services.OrderManagerService;
import services.framework.Service;
import services.framework.ServiceConstructor;

import java.util.HashMap;

public class PaymentService extends Service {

  /**
   * A mapping of orders by tables.
   */
  private HashMap<Table, Bill> billsByTable;

  private OrderManagerService orderManagerService;

  @ServiceConstructor
  public PaymentService(OrderManagerService orderManagerService) {
    billsByTable = new HashMap<>();
    this.orderManagerService = orderManagerService;
  }

  /**
   * Registers a table.
   *
   * @param table the table to be registered.
   * @return true iff the table is not already registered.
   */
  public boolean registerTable(Table table) {
    if (billsByTable.containsKey(table)) {
      return false;
    } else {
      billsByTable.put(table, new Bill());
      return true;
    }
  }

  /**
   * Unregisters a table.
   *
   * @param table the table to be unregistered.
   */
  public void unregisterTable(Table table) {
    billsByTable.remove(table);
  }

  /**
   * Registers an order placed by a table.
   *
   * @param table the table from which the order originated.
   * @param order the order placed by the table.
   * @return true iff the specified table is registered.
   */
  public boolean registerOrder(Table table, Order order) {
    if (billsByTable.containsKey(table)) {
      billsByTable.get(table).addOrder(order);
      return true;
    } else {
      return false;
    }
  }

  /**
   * Returns the bill associated with a table.
   *
   * @param table the table from which the bill should be retrieved.
   * @return the bill of the specified table or null if the table is
   *         unregistered.
   */
  public Bill getBill(Table table) {
    return billsByTable.get(table);
  }

  /**
   * Returns a string printing of a bill.
   *
   * @param table the bill to be printed.
   * @return a string representation of the specified bill.
   */
  public String printBill(Table table) {
    StringBuilder accumulator = new StringBuilder();
    // Format: Bill for Table #TableNumber
    accumulator.append("Bill for Table #");
    accumulator.append(table.getTableNumber());
    accumulator.append(System.lineSeparator());
    // Format: Item:Price
    for (Order order : billsByTable.get(table).getOrders()) {
      accumulator.append('\t');
      accumulator.append(order.getMenuItem().getName());
      accumulator.append(":\t$");
      accumulator.append(order.getMenuItem().getPrice());
      accumulator.append(System.lineSeparator());
    }
    return accumulator.toString();
  }

}
