package kitchen;

import java.util.HashMap;

/**
 * Inventory represents the stock of Ingredients.
 */
public class Inventory {
    /**
     * The HashMap of each Ingredient with the amount of remaining items in stock.
     */
    private HashMap<Ingredient, Integer> inventory;

    /**
     * The HashMap of each Ingredient with its threshold for restock.
     */
    private HashMap<Ingredient, Integer> threshold;

    /**
     * Class constructor of an Inventory.
     */
    public Inventory() {
        inventory = new HashMap<>();
        threshold = new HashMap<>();
    }

    /**
     * Checks the remaining amount of ingredient
     *
     * @param ingredient the ingredient to be checked.
     * @return the remaining amount of ingredient.
     */
    public int getLeftOver(Ingredient ingredient) {
        return inventory.get(ingredient);
    }

    /**
     * Returns the full inventory of the amount of remaining items for each ingredient.
     *
     * @return the inventory HashMap
     */
    public HashMap<Ingredient, Integer> getInventory() {
        return inventory;
    }

    /**
     * Returns the full map of threshold accordingly for each ingredient.
     *
     * @return the threshold HashMap
     */
    public HashMap<Ingredient, Integer> getThreshold() {
        return threshold;
    }

    /**
     * Checks if needing to reorder ingredient.
     * If so, reorders the ingredient when the amount of remaining items is below the threshold.
     *
     * @param ingredient the ingredient to be checked for reorder
     * @return true if needing to reorder ingredient; false otherwise
     */
    public boolean reOrder(Ingredient ingredient) {
        int num = inventory.get(ingredient);
        int limit = threshold.get(ingredient);
        return num < limit;     // TODO: update requests.txt
    }

    /**
     * Returns the string representation of this.
     *
     * @return the string representation of the full inventory
     */
    public String toString() {
        String s = "{";
        for (Ingredient ingredient : inventory.keySet()) {
            s += "( " + ingredient.getName() + ", " + inventory.get(ingredient) + " ), ";
        }
        return s.substring(0, s.length() - 2) + "}";
    }
}
