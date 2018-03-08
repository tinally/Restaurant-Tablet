package payment;

import kitchen.Order;
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
    private ArrayList<Order> orders;

    /**
     * Constructs a new Bill.
     */
    public Bill() {
        orders = new ArrayList<>();
    }

    /**
     * Returns the order items associated with this bill.
     *
     * @return the order items associated with this bill.
     */
    public ArrayList<Order> getOrders() {
        return orders;
    }

    /**
     * Adds a new Order to the bill.
     *
     * @param order the Order to be added to the bill
     */
    public void addOrder(Order order) {
        orders.add(order);
    }
}
