package kitchen;

import restaurant.OrderItem;
import java.util.*;

public class Order {
    private List<OrderItem> orders;
    private int tableNumber;
    private Server server;
    private boolean isDelivered;

    public Order(List<OrderItem> orders, int tableNumber, Server server) {
        this.orders = orders;
        this.tableNumber = tableNumber;
        this.server = server;
        this.isDelivered = false;
    }

    public List<OrderItem> getOrders() {
        return orders;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public Server getServer() {
        return server;
    }

    public boolean delivered() {
        return isDelivered;
    }

    public void add(OrderItem oi) {
        orders.add(oi);
    }

    public boolean subtract(OrderItem oi) {
        if (isDelivered) {
            return false;
        } else {
            orders.remove(oi);
            return true;
        }
    }
}
