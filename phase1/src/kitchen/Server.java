package kitchen;

import events.EventEmitter;
import events.RestaurantEventHandler;
import events.eventtypes.OrderCompleteEvent;
import events.eventtypes.OrderInputEvent;
import services.BillPrinterService;
import services.framework.*;
import restaurant.*;
import java.util.*;

public class Server extends Service implements RestaurantEventHandler<OrderCompleteEvent> {

    private String name;
    private int tableNumber;
    private BillPrinterService bp;
    private Inventory inventory;

    @ServiceConstructor
    public Server(EventEmitter em, BillPrinterService bp, String name, int tableNumber, Inventory inventory) {
        this.bp = bp;
        this.name = name;
        this.tableNumber = tableNumber;
        this.inventory = inventory;
    }

    public void handle(OrderCompleteEvent event, Object obj) {
        OrderItem oi = event.getOrderItem();
        MenuItem mi = oi.getMenuItem();
        Map<Ingredient, Integer> inventory = this.inventory.getInventory();
        Map<Ingredient, Integer> ingredients = mi.getIngredients();
        for (Ingredient i : ingredients.keySet()) {
            int deduct = ingredients.get(i);
            int current = inventory.get(i);
            if (current >= deduct) {
                inventory.put(i, current - deduct);
                // TODO: call reorder
            } else {

            }
        }
    }

    public void addOrder() {

    }

    public void serve() {

    }

    public void checkout() {

    }
}
