package services;

import kitchen.Order;
import payment.Bill;
import restaurant.Table;
import services.framework.ServiceConstructor;

import java.util.HashMap;

/**
 * A PaymentManagerService keeps track of all Tables within the Restaurant and
 * the Bill associated with each Table.
 */
public class PaymentManagerService {

    /**
     * A mapping of Orders by Tables.
     */
    private HashMap<Table, Bill> billsByTable;

    /**
     * PaymentManagerService constructor.
     */
    @ServiceConstructor
    public PaymentManagerService() {
        billsByTable = new HashMap<>();
    }

    /**
     * Registers a Table.
     * @param table the Table to be registered.
     * @return true iff the Table is not already registered.
     */
    public boolean registerTable(Table table) {
        if (billsByTable.containsKey(table)) {
            return false;
        } else {
            billsByTable.put(table, new Bill(table));
            return true;
        }
    }

    /**
     * Unregisters a Table.
     * @param table the Table to be unregistered.
     */
    public void unregisterTable(Table table) {
        billsByTable.remove(table);
    }

    /**
     * Registers an Order placed by a Table.
     * @param table the Table from which the Order originated.
     * @param order the Order placed by the Table.
     * @return
     */
    public boolean registerOrder(Table table, Order order) {
        if (billsByTable.containsKey(table)) {
            billsByTable.get(table).addOrder(order);
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param table
     * @return the Bill of the specified Table.
     */
    public Bill getBill(Table table) {
        return billsByTable.get(table);
    }

}
