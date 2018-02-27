package services;

import payment.Bill;
import services.framework.Service;
import services.framework.ServiceConstructor;

import java.util.ArrayList;

/**
 * This Service prints one or more Bills according to specification.
 */
// TODO: Extend Service class?
public class BillPrinterService extends Service {

    private ArrayList<Bill> bills;

    @ServiceConstructor
    public BillPrinterService() {
        bills = new ArrayList<>();
    }

    /**
     * @return a String printing of all bills
     */
    public String printBills() {
        String accumulator = "";
        for (Bill bill : bills) {
            // TODO: Implement this
            accumulator += "";
            //for ()
        }

        return accumulator;
    }

    /**
     * @param id the ID of the Bill to print.
     * @return a String printing of the Bill with the specific ID
     */
    public String printBill(int id) {
        return null;
    }

}
