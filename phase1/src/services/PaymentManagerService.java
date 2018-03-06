package services;

import kitchen.Order;
import payment.Bill;
import restaurant.Table;
import services.framework.ServiceConstructor;

import java.util.HashMap;

/**
 * A PaymentManagerService keeps track of all tables within the restaurant and
 * the bill associated with each table.
 */
public class PaymentManagerService {

  /**
   * A mapping of orders by tables.
   */
  private HashMap<Table, Bill> billsByTable;

  /**
   * Constructs a new PaymentManagerService.
   */
  @ServiceConstructor
  public PaymentManagerService() {
    billsByTable = new HashMap<>();
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
      billsByTable.put(table, new Bill(table));
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

}
