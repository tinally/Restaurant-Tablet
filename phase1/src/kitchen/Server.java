package kitchen;

import events.EventEmitter;
import events.RestaurantEventHandler;
import events.eventtypes.OrderCompleteEvent;
import events.eventtypes.OrderInputEvent;
import services.BillPrinterService;
import services.framework.*;
import restaurant.*;
import java.util.*;

public class Server extends Service implements RestaurantEventHandler<OrderInputEvent> {

    private String name;
    private int tableNumber;
    private BillPrinterService bp;

    @ServiceConstructor
    public Server(EventEmitter em, BillPrinterService bp, String name, int tableNumber) {
        this.bp = bp;
        this.name = name;
        this.tableNumber = tableNumber;
    }

    public void handle(OrderCompleteEvent event, Object obj) {
        OrderItem oi = event.getOrderItem();
        MenuItem mi = oi.getMenuItem();
        HashMap<Ingredient, Integer> ingredients = mi.getIngredients();
        for (Ingredient i : ingredients.keySet()) {

        }
    }

    public void addOrder() {

    }

    public void serve() {

    }

    public String checkout() {
        return bp.printBill();
    }
}
