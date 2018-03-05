package kitchen;

import restaurant.OrderItem;

import java.util.*;

public class Order {
    /**
     * List of OrderItems that are ordered.
     */
    private List<OrderItem> items;

    /**
     * The table number of the table that sends this Order.
     */
    private int tableNumber;

    /**
     * The server responsible for this Order.
     */
//    private Server server;

    /**
     * True if the order is delivered and false otherwise.
     */
    private boolean isDelivered;

    /**
     * Class constructor specifying the list of OrderItems, the table number, and the server of this Order.
     *
     * @param items       the OrderItems being ordered
     * @param tableNumber the table number of the table that sends this Order
    / * @param server      the server responsible for this Order
     */
    public Order(List<OrderItem> items, int tableNumber/*, Server server*/) {
        this.items = items;
        this.tableNumber = tableNumber;
//        this.server = server;
        this.isDelivered = false;
    }

    /**
     * Returns the list of OrderItems being ordered.
     *
     * @return the items being ordered
     */
    public List<OrderItem> getItems() {
        return items;
    }

    /**
     * Returns the table number of the table that sends this Order.
     *
     * @return the table number
     */
    public int getTableNumber() {
        return tableNumber;
    }

    /**
     * Returns the server responsible for this Order.
     *
     * @return the server
     */
//    public Server getServer() {
//        return server;
//    }

    /**
     * Checks whether the Order has been delivered to the table.
     *
     * @return true if it is delivered; false otherwise
     */
    public boolean delivered() {
        return isDelivered;
    }

    /**
     * Adds a new OrderItem to the order.
     *
     * @param oi the OrderItem to be added to the order
     */
    public void add(OrderItem oi) {
        items.add(oi);
    }

    /**
     * Subtracts an OrderItem that was ordered only when the OrderItem hasn't been delivered yet.
     * @param oi the OrderItem to be removed from the order
     * @return true if the OrderItem can be removed; false otherwise
     */
    public boolean subtract(OrderItem oi) {
        if (isDelivered) {
            return false;
        } else {
            items.remove(oi);
            return true;
        }
    }
}
