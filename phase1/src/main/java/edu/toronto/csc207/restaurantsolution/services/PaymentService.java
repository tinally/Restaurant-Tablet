package edu.toronto.csc207.restaurantsolution.services;

import edu.toronto.csc207.restaurantsolution.model.Order;
import edu.toronto.csc207.restaurantsolution.model.Bill;
import edu.toronto.csc207.restaurantsolution.model.Table;
import edu.toronto.csc207.restaurantsolution.framework.services.Service;
import edu.toronto.csc207.restaurantsolution.framework.services.ServiceConstructor;

import java.util.ArrayList;
import java.util.HashMap;

public class PaymentService extends Service {

  /**
   * A mapping of orders by tables.
   */
  private HashMap<Table, Bill> billsByTable;

  // TODO: Remove this
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
    // TODO: Use putIfAbsent
    // TODO: Remove boolean?
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
    double total = 0.0;
    StringBuilder accumulator = new StringBuilder();

    // Bill for Table #TableNumber
    accumulator.append("Bill for Table #");
    accumulator.append(table.getTableNumber());
    accumulator.append(System.lineSeparator());

    // Item: Price
    for (Order order : billsByTable.get(table).getOrders()) {
      accumulator.append(printOrder(order));
      accumulator.append(System.lineSeparator());

      // Add price to the total
      total += order.getMenuItem().getPrice();
    }

    // Total: Price
    accumulator.append("\tTotal");
    accumulator.append(":\t$");
    accumulator.append(total);

    return accumulator.toString();
  }

  /**
   * Returns a string printing of a single order.
   *
   * @param order the order to be printed.
   * @return a string representation of the specified order.
   */
  private String printOrder(Order order) {
    // String used since we are not concatenating inside a loop
    String accumulator = "\t";

    // Item: Price
    accumulator += order.getMenuItem().getName();
    accumulator += ":\t$";
    accumulator += order.getMenuItem().getPrice();
    accumulator += System.lineSeparator();

    return accumulator;
  }

}
