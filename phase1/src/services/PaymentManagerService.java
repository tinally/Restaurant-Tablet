package services;

import kitchen.Order;
import payment.Bill;
import restaurant.Table;
import services.framework.ServiceConstructor;

import java.util.HashMap;

/**
 * A PaymentManagerService keeps track of all Tables within the Restaurant and the
 * Bills associated with each Table.
 */
public class PaymentManagerService {

    /**
     * A mapping of Orders by Tables.
     */
    private HashMap<Table, Bill> billsByTable;

    @ServiceConstructor
    public PaymentManagerService() {
        billsByTable = new HashMap<>();
    }

    public boolean registerTable(Table table) {
        if (billsByTable.containsKey(table)) {
            return false;
        } else {
            billsByTable.put(table, new Bill());
            return true;
        }
    }

    /**
     * Registers a Table with the
     * @param table
     */
    public void unregisterTable(Table table) {
        billsByTable.remove(table);
    }

    public boolean registerOrder(Table table, Order order) {
        if (billsByTable.containsKey(table)) {
            billsByTable.get(table).addOrder(order);
            return true;
        } else {
            return false;
        }
    }

    public Bill getBill(Table table) {
        return billsByTable.get(table);
    }

}
