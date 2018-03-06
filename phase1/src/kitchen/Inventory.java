package kitchen;

import events.eventtypes.IngredientRequiresReorderEvent;
import org.yaml.snakeyaml.emitter.Emitter;

import java.util.*;
import events.*;

/**
 * Inventory represents the stock of Ingredients.
 */
public class Inventory {
    /**
     * The HashMap of each Ingredient with the amount of remaining items in stock.
     */
    private Map<Ingredient, Integer> inventory;

    /**
     * The HashMap of each Ingredient with its threshold for restock.
     */
    private Map<Ingredient, Integer> threshold;
    private EventEmitter em;

    /**
     * Class constructor of an Inventory.
     */
    public Inventory(EventEmitter em) {
        this.em = em;
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
    public Map<Ingredient, Integer> getInventory() {
        return inventory;
    }

    /**
     * Returns the full map of threshold accordingly for each ingredient.
     *
     * @return the threshold HashMap
     */
    public Map<Ingredient, Integer> getThreshold() {
        return threshold;
    }

    public void addToInventory(Ingredient ingredient, int num) {
        int leftover = inventory.get(ingredient);
        inventory.put(ingredient, leftover + num);
    }

    public void removeFromInventory(Ingredient ingredient, int num) {
        int leftover = inventory.get(ingredient);
        if (leftover > num) {
            inventory.put(ingredient, leftover - num);
        }
        reOrder(ingredient);
    }

    /**
     * Checks if needing to reorder ingredient.
     * If so, reorders the ingredient when the amount of remaining items is below the threshold.
     *
     * @param ingredient the ingredient to be checked for reorder
     */
    public void reOrder(Ingredient ingredient) {
        int num = inventory.get(ingredient);
        int limit = threshold.get(ingredient);
        if (num < limit) {
            em.onEvent(new IngredientRequiresReorderEvent(ingredient));
            // TODO: update requests.txt
        }
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
