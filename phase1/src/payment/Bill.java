package payment;

import kitchen.Order;
import restaurant.OrderItem;

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

//    /**
//     * The number of ways to split this Bill among customers at the Table.
//     */
//    // TODO: Move this to PaymentManagerService
//    private int splits;

    /**
     * Bill constructor.
     */
    public Bill() {
        orderItems = new ArrayList<>();
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
