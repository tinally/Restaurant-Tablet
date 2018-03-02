package payment;

import restaurant.MenuItem;

import java.util.ArrayList;

/**
 * A Bill represents a collection of Bill Items that must be paid for
 * by a Table.
 */
public class Bill {

    /**
     * The MenuItems that are to be paid for with this Bill.
     */
    private ArrayList<MenuItem> menuItems;

    /**
     * The number of ways to split this Bill among customers at the Table.
     */
    private int splits;

    /**
     * Bill constructor.
     */
    public Bill() {
        menuItems = new ArrayList<>();
        splits = 1;
    }

    public void addMenuItem(MenuItem menuItem) {
        menuItems.add(menuItem);
    }

    public ArrayList<MenuItem> getMenuItems() {
        return menuItems;
    }

}
