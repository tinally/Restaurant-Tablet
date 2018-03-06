package kitchen;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    private int orderNumber;

    private OrderStatus status;
    private Order() {

    }

    /**
     * Class constructor specifying the list of OrderItems, the table number, and the server of this Order.
     *
     * @param items       the OrderItems being ordered
     * @param tableNumber the table number of the table that sends this Order
     */
    public Order(List<OrderItem> items, int tableNumber, String serverName, int orderNumber) {
        this.items = items;
        this.tableNumber = tableNumber;
        this.status = OrderStatus.CREATED;
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

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public int getOrderNumber() {
        return orderNumber;
    }
}
