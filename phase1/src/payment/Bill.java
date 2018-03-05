package payment;

import kitchen.Order;
import restaurant.OrderItem;
import restaurant.Table;

import java.util.ArrayList;

/**
 * A Bill represents a collection of Bill Items that must be paid for
 * by a Table.
 */
public class Bill {

    /**
     * The MenuItems that are to be paid for with this Bill.
     */
    private ArrayList<OrderItem> orderItems;

    /**
     * The Table to which this Bill belongs.
     */
    private Table table;

    /**
     * Constructs a new Bill for the specified Table.
     * @param table the Table to which this Bill belongs.
     */
    public Bill(Table table) {
        orderItems = new ArrayList<>();
        this.table = table;
    }

    /**
     * @return the OrderItems associated with this Bill.
     */
    public ArrayList<OrderItem> getOrderItems() {
        return orderItems;
    }

    /**
     * @param order the Order to be added to this Bill.
     */
    public void addOrder(Order order) {
        orderItems.addAll(order.getItems());
    }

}
