package edu.toronto.csc207.restaurantsolution.services;

import edu.toronto.csc207.restaurantsolution.model.Order;
import edu.toronto.csc207.restaurantsolution.model.Bill;
import edu.toronto.csc207.restaurantsolution.framework.services.Service;
import edu.toronto.csc207.restaurantsolution.framework.services.ServiceConstructor;

import java.util.HashMap;
import java.util.StringJoiner;

/**
 * Manages all active tableNumbers (deemed "registered") and keeps track of bills
 * associated with those tableNumbers.
 * <p>
 * Provides bill modification and printing functionality.
 */
public class PaymentService extends Service {


    /**
     * A mapping of orders by tableNumbers.
     */
    private HashMap<Integer, Bill> billsByTableNumber;

    /**
     * Constructs a new payment service.
     */
    @ServiceConstructor
    public PaymentService() {
        billsByTableNumber = new HashMap<>();
    }

    /**
     * Registers a tableNumber with an empty bill if it has not already been registered.
     *
     * @param tableNumber the tableNumber to be registered.
     */
    public void registerTable(int tableNumber) {
        billsByTableNumber.putIfAbsent(tableNumber, new Bill());
    }

    /**
     * Unregisters a tableNumber.
     *
     * @param tableNumber the tableNumber to be unregistered.
     */
    public void unregisterTable(int tableNumber) {
        billsByTableNumber.remove(tableNumber);
    }

    /**
     * Registers an order placed by a tableNumber on that tableNumber's bill if that tableNumber
     * has been registered.
     *
     * @param tableNumber the tableNumber from which the order originated.
     * @param order the order placed by the tableNumber.
     */
    public void registerOrder(int tableNumber, Order order) {
        if (billsByTableNumber.containsKey(tableNumber)) {
            billsByTableNumber.get(tableNumber).addOrder(order);
        }
    }

    /**
     * Returns the bill associated with a tableNumber.
     *
     * @param tableNumber the tableNumber from which the bill should be retrieved.
     * @return the bill of the specified tableNumber or null if the tableNumber is
     * unregistered.
     */
    public Bill getBill(int tableNumber) {
        return billsByTableNumber.get(tableNumber);
    }

    /**
     * Returns a string printing of a bill.
     *
     * @param tableNumber the bill to be printed.
     * @return a string representation of the specified bill.
     */
    public String printBill(int tableNumber) {
        double total = 0.0;
        StringJoiner accumulator = new StringJoiner(System.lineSeparator());

        // Bill for int #intNumber
        accumulator.add("Bill for int #" + tableNumber);

        // Item: Price
        for (Order order : billsByTableNumber.get(tableNumber).getOrders()) {
            accumulator.add(printOrder(order));

            // Add price to the total
            total += order.getMenuItem().getPrice();
        }

        // Total: Price
        accumulator.add("Total:\t$" + total);

        return accumulator.toString();
    }

    /**
     * Returns a string printing of a single order.
     *
     * @param order the order to be printed.
     * @return a string representation of the specified order.
     */
    private String printOrder(Order order) {
        // String used since we are not concatenating inside a loop
        String accumulator = "\t";

        // Item: Price
        accumulator += order.getMenuItem().getName();
        accumulator += ":\t$";
        accumulator += order.getMenuItem().getPrice();

        return accumulator;
    }
}
