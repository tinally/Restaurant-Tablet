package services;

import payment.Bill;
import restaurant.OrderItem;
import services.framework.Service;
import services.framework.ServiceConstructor;

import java.util.HashMap;

/**
 * This Service prints one or more Bills according to specification.
 */
public class BillPrinterService extends Service {

    /**
     * The Bills to be printed.
     */
    private HashMap<Integer, Bill> bills;

    /**
     * BillPrinterService constructor.
     */
    @ServiceConstructor
    public BillPrinterService() {
        bills = new HashMap<>();
    }

    /**
     * Add a new Bill to be printed.
     * @param id the ID with which this Bill will be associated.
     * @return false iff the given ID is already associated with a Bill.
     */
    public boolean addBill(Integer id, Bill bill) {
        if (bills.containsKey(id)) {
            return false;
        } else {
            bills.put(id, bill);
            return true;
        }
    }

    // TODO: Decide how to format this
    /**
     * @param bill the Bill to be printed.
     * @return a String representation of the Bill.
     */
    public String printBill(Bill bill) {
        StringBuilder accumulator = new StringBuilder();
        // Format: ITEM:PRICE
        for (OrderItem orderItem : bill.getOrderItems()) {
            accumulator.append('\t');
            accumulator.append(orderItem.toString());
            accumulator.append(':');
            // TODO: Change to getPrice() - discount should always be applied
            accumulator.append(orderItem.getMenuItem().getOriginalPrice());
            accumulator.append(System.lineSeparator());
        }
        return accumulator.toString();
    }

    /**
     * @return a String representation of all bills.
     */
    public String printBills() {
        StringBuilder accumulator = new StringBuilder();
        for (Integer billId : bills.keySet()) {
            accumulator.append(printBill(billId));
        }
        return accumulator.toString();
    }

    /**
     * @param id the ID of the Bill to print.
     * @return a String representation of the Bill with the specified ID.
     */
    public String printBill(Integer id) {
        StringBuilder accumulator = new StringBuilder();
        Bill bill = bills.get(id);
        // First, print out the ID of the bill being printed
        accumulator.append(id);
        accumulator.append(System.lineSeparator());
        // Then, print each menu item of the bill (indent included) in format
        // ITEM:PRICE
        for (OrderItem orderItem : bill.getOrderItems()) {
            accumulator.append('\t');
            accumulator.append(orderItem.toString());
            accumulator.append(':');
            // TODO: Change to getPrice() - discount should always be applied
            accumulator.append(orderItem.getMenuItem().getOriginalPrice());
            accumulator.append(System.lineSeparator());
        }
        return accumulator.toString();
    }

}
