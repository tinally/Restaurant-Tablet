package kitchen;

import events.EventEmitter;
import events.RestaurantEventHandler;
import events.eventtypes.IngredientRequiresReorderEvent;
import events.eventtypes.OrderCompleteEvent;
import events.eventtypes.OrderInputEvent;
import services.BillPrinterService;
import services.framework.*;
import restaurant.*;
import java.util.*;

public class Server extends Service {


    private String name;
    private int tableNumber;
    private EventEmitter em;
    private BillPrinterService bp;
    private Inventory inventory;
    private Queue<OrderItem> delivery;

    @ServiceConstructor
    public Server(EventEmitter em, BillPrinterService bp, String name, int tableNumber, Inventory inventory) {
        this.em = em;
        this.bp = bp;
        this.name = name;
        this.tableNumber = tableNumber;
        this.inventory = inventory;
        em.registerEventHandler(this::updateIngredient, OrderCompleteEvent.class);
    }

    private void updateIngredient(OrderCompleteEvent event, Object obj) {
        OrderItem oi = event.getOrderItem();
        MenuItem mi = oi.getMenuItem();
        Map<Ingredient, Integer> inventory = this.inventory.getInventory();
        Map<Ingredient, Integer> ingredients = mi.getIngredients();
        for (Ingredient i : ingredients.keySet()) {
            int deduct = ingredients.get(i);
            int current = inventory.get(i);
            if (current >= deduct) {
                inventory.put(i, current - deduct);
                em.onEvent(new IngredientRequiresReorderEvent(i),this);
            } else {
                // TODO:
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
