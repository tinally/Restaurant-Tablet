package kitchen;

import restaurant.MenuItem;

import java.util.*;

public class Order {

    private MenuItem menuItem;

    /**
     * The table number of the table that sends this Order.
     */
    private int tableNumber;

    private int orderNumber;

    private OrderStatus status;

    private Order() {

    }

    /**
     * Class constructor specifying the list of OrderItems, the table number, and the server of this Order.
     *
     * @param menuItem       the OrderItems being ordered
     * @param tableNumber the table number of the table that sends this Order
     */
    public Order(MenuItem menuItem,
                 int tableNumber,
                 String serverName,
                 int orderNumber) {
        this.menuItem = menuItem;
        this.tableNumber = tableNumber;
        this.status = OrderStatus.CREATED;
    }

    /**
     * Returns the table number of the table that sends this Order.
     *
     * @return the table number
     */
    public int getTableNumber() {
        return tableNumber;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }
}
