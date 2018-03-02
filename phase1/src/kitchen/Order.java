package kitchen;

import restaurant.OrderItem;
import java.util.*;

public class Order {
    private List<OrderItem> items;
    private int tableNumber;
    private Server server;
    private boolean isDelivered;

    public Order(List<OrderItem> items, int tableNumber, Server server) {
        this.items = items;
        this.tableNumber = tableNumber;
        this.server = server;
        this.isDelivered = false;
    }

    public List<OrderItem> getItems() {
        return items;
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
        items.add(oi);
    }

    public boolean subtract(OrderItem oi) {
        if (isDelivered) {
            return false;
        } else {
            items.remove(oi);
            return true;
        }
    }
}
