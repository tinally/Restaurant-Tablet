package edu.toronto.csc207.restaurantsolution.services;

import edu.toronto.csc207.restaurantsolution.framework.services.Service;
import edu.toronto.csc207.restaurantsolution.framework.services.ServiceConstructor;
import edu.toronto.csc207.restaurantsolution.model.Bill;
import edu.toronto.csc207.restaurantsolution.model.Order;

import java.util.HashMap;
import java.util.StringJoiner;

/**
 * Manages all active tables by number and allows for registration of orders
 * with those tables.
 * <p>
 * Provides bill modification and printing functionality.
 */
public class PaymentService extends Service {
  /**
   * A mapping of orders by table numbers.
   */
  private HashMap<Integer, Bill> billsByTableNumber;

  /**
   * Constructs a new payment service.
   */
  @ServiceConstructor
  public PaymentService() {
    billsByTableNumber = new HashMap<>();
  }

  /**
   * Registers a table with an empty bill if a table with the specified
   * number has not yet been registered.
   *
   * @param tableNumber the number of the table to be registered.
   */
  public void registerTable(int tableNumber) {
    billsByTableNumber.putIfAbsent(tableNumber, new Bill());
  }

  /**
   * Unregisters a table.
   *
   * @param tableNumber the number of the table to be unregistered.
   */
  // TODO: Use in phase 2
  public void unregisterTable(int tableNumber) {
    billsByTableNumber.remove(tableNumber);
  }

  /**
   * Registers an order placed by a table if that table has been registered.
   *
   * @param tableNumber the table number of the originating table.
   * @param order       the order placed by the table.
   */
  public void registerOrder(int tableNumber, Order order) {
    if (billsByTableNumber.containsKey(tableNumber)) {
      billsByTableNumber.get(tableNumber).addOrder(order);
    }
  }

  /**
   * Returns the bill associated with a table.
   *
   * @param tableNumber the number of the table.
   * @return the bill of the specified table or null if the table is
   * unregistered.
   */
  // TODO: Use in phase 2
  public Bill getBill(int tableNumber) {
    return billsByTableNumber.get(tableNumber);
  }

  /**
   * Returns a string printing of a bill for a table specified by number.
   *
   * @param tableNumber the number of the table.
   * @return a string representation of the bill.
   */
  public String printBill(int tableNumber) {
    double total = 0.0;
    StringJoiner accumulator = new StringJoiner(System.lineSeparator());

    // Bill for int #intNumber
    accumulator.add("Bill for int #" + tableNumber);

    // Item: Price
    for (Order order : billsByTableNumber.get(tableNumber).getOrders()) {
      accumulator.add(printOrder(order));

      // Add price to the total
      total += order.getMenuItem().getPrice();
    }

    // Total: Price
    accumulator.add("Total:\t$" + total);

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

    return accumulator;
  }
}
