package kitchen;

import events.newevents.IngredientReorderEvent;

import java.util.*;

import events.*;
import events.newevents.IngredientRestockEvent;

/**
 * Inventory represents the stock of Ingredients of this restaurant.
 */
public class Inventory {
    /**
     * The HashMap of each Ingredient with the amount of remaining items in stock.
     */
    private Map<Ingredient, Integer> inventory;

    private EventEmitter emitter;

    /**
     * Ingredients to reorder
     */
    private ArrayList<Ingredient> ingToReorder;

    /**
     * Class constructor of an Inventory.
     */
    public Inventory(EventEmitter emitter, Map<Ingredient, Integer> initialInventory) {
        this.emitter = emitter;
        this.inventory = initialInventory;
        this.ingToReorder = new ArrayList<>();
        emitter.registerEventHandler(e -> {
            this.addToInventory(e.getIngredient(), e.getIngredient().getReorderAmount());
        }, IngredientRestockEvent.class);
        emitter.removeEventHandler(e -> {
            this.reOrder(e.getIngredient());
        }, IngredientReorderEvent.class);
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
    public int getAmountRemaining(Ingredient i) {
        if (inventory.containsKey(i)) {
            return inventory.get(i);
        }
        return 0;
    }

    /**
     * Returns the full map of threshold accordingly for each ingredient.
     *
     * @return the threshold HashMap
     */
    // todo: refactor this out.
    public int getReorderThreshold(Ingredient i) {
        return i.getReorderThreshold();
    }

    /**
     * Add the ingredient to the inventory
     *
     * @param ingredient the ingredient to be added
     * @param num        the number of units to be added
     */
    public void addToInventory(Ingredient ingredient, int num) {
        int leftover = inventory.get(ingredient); //TODO: num could be changed to ingredient.getReorderAmount()
        inventory.put(ingredient, leftover + num);
        ingToReorder.remove(ingredient);
        Request.write(ingToReorder);
    }

    /**
     * Remove the ingredient from the inventory when they are used by the chef.
     *
     * @param ingredient ingredient used
     * @param num        number of ingredient used
     */
    public void removeFromInventory(Ingredient ingredient, int num) {
        int leftover = inventory.get(ingredient);
        if (leftover > num) {
            inventory.put(ingredient, leftover - num);
        }
        reOrder(ingredient);
    }

    /**
     * Add the ingredient to a request file if the ingredient is below the threshold.
     *
     * @param ingredient the ingredient to be checked for reorder
     */
    private void reOrder(Ingredient ingredient) {
        int num = inventory.get(ingredient);
        int limit = ingredient.getReorderThreshold();
        if (num < limit) {
//            emitter.onEvent(new IngredientReorderEvent(ingredient));
//            ingToReorder.add(ingredient);
            Request.write(ingToReorder);
        }
    }

    /**
     * Returns the string representation of this.
     *
     * @return the string representation of the full inventory
     */
    public String toString() {
        StringBuilder sBuilder = new StringBuilder("{");
        for (Ingredient ingredient : inventory.keySet()) {
            sBuilder.append("( ")
                    .append(ingredient.getName())
                    .append(", ")
                    .append(inventory.get(ingredient)).append(" ), ");
        }
        String s = sBuilder.toString();
        return s.substring(0, s.length() - 2) + "}";
    }
}
