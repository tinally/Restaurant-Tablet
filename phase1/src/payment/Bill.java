package payment;

import java.util.ArrayList;

/**
 * A Bill represents a collection of Bill Items that must be paid for
 * by a customer.
 */
public class Bill {

    /**
     * A collection of BillItems that are to be paid with this Bill.
     */
    @Deprecated
    private ArrayList<BillItem> billItems;

    // TODO: Javadoc / Implement once MenuItems implemented
//    private ArrayList<MenuItem> menuItems;

    /**
     * The number of ways to split this Bill among customers at a Table.
     */
    private int splits;

    public Bill() {
        // TODO: Implement once MenuItems implemented
//        menuItems = new ArrayList<>();
        splits = 1;
    }

    // TODO: Javadoc / Implement once MenuItems implemented
//    public void addMenuItem(MenuItem menuItem) {
//
//    }

    // TODO: Javadoc / Implement once MenuItems implemented
//    public MenuItem[] getMenuItems() {
//
//    }

}
