package kitchen;

import events.EventEmitter;
import events.eventtypes.OrderRejectEvent;
import events.newevents.OrderChangedEvent;
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
    private PriorityQueue<Order> delivery;

    @ServiceConstructor
    public Server(EventEmitter em, BillPrinterService bp, String name, int tableNumber, Inventory inventory) {
        this.em = em;
        this.bp = bp;
        this.name = name;
        this.tableNumber = tableNumber;
        this.inventory = inventory;
        em.registerEventHandler(this::updateIngredient, OrderChangedEvent.class);
     }

    private void updateIngredient(OrderChangedEvent event) {
        if(!event.getNewStatus() == OrderStatus.)
        OrderItem oi = event.getOrderItem();
        MenuItem mi = oi.getMenuItem();
        Map<Ingredient, Integer> ingredients = mi.getIngredients();
        for (Ingredient i : ingredients.keySet()) {
            int deduct = ingredients.get(i);
            this.inventory.removeFromInventory(i, deduct);
        }
    }

    private void rejectOrderItem(OrderRejectEvent event) {
        
    }

    public void addOrder() {

    }

    public void serve() {

    }

    public void checkout() {

    }
}
