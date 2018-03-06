package payment;

import kitchen.Order;
import restaurant.OrderItem;
import restaurant.Table;

import java.util.ArrayList;

/**
 * A Bill represents a collection of outstanding order items to be paid by
 * a table of customers.
 */
public class Bill {

  /**
   * The order items that are outstanding (to be paid for) with this bill.
   */
  private ArrayList<OrderItem> orderItems;

  /**
   * The table to which this bill belongs.
   */
  private Table table;

  /**
   * Constructs a new Bill for the specified table.
   *
   * @param table the table to which this bill belongs.
   */
  public Bill(Table table) {
    orderItems = new ArrayList<>();
    this.table = table;
  }

  /**
   * Returns the order items associated with this bill.
   *
   * @return the order items associated with this bill.
   */
  public ArrayList<OrderItem> getOrderItems() {
    return orderItems;
  }

  /**
   * Returns the table associated with this bill.
   *
   * @return the table associated with this bill.
   */
  public Table getTable() {
    return table;
  }

  /**
   * Adds an order to this bill.
   *
   * @param order the order whose items will be added to this bill.
   */
  public void addOrder(Order order) {
    orderItems.addAll(order.getItems());
  }

}
