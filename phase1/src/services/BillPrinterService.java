package services;

import payment.Bill;
import restaurant.OrderItem;
import services.framework.Service;
import services.framework.ServiceConstructor;

import java.util.ArrayList;

/**
 * This Service prints one or more Bills according to specification.
 */
public class BillPrinterService extends Service {

    /**
     * BillPrinterService constructor.
     */
    @ServiceConstructor
    public BillPrinterService() {
    }

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
     * @return a String representation of a collection of Bills.
     * @param bills an ArrayList of Bills.
     */
    public String printBills(ArrayList<Bill> bills) {
        StringBuilder accumulator = new StringBuilder();
        for (Bill bill : bills) {
            accumulator.append(printBill(bill));
        }

        return accumulator.toString();
    }

    /**
     * @return a String representation of a collection of Bills.
     * @param bills an Array of Bills.
     */
    public String printBills(Bill[] bills) {
        StringBuilder accumulator = new StringBuilder();
        for (Bill bill : bills) {
            accumulator.append(printBill(bill));
        }

        return accumulator.toString();
    }

}
