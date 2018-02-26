package kitchen;

import java.util.HashMap;

public class Inventory {
    private HashMap<Ingredient, Integer> inventory;
    private HashMap<Ingredient, Integer> threshold;

    public Inventory() {
        inventory = new HashMap<>();
        threshold = new HashMap<>();
    }

    public int getLeftOver(Ingredient ingredient) {
        return inventory.get(ingredient);
    }

    public HashMap<Ingredient, Integer> getInventory() {
        return inventory;
    }

    public HashMap<Ingredient, Integer> getThreshold() {
        return threshold;
    }

    public boolean reOrder(Ingredient ingredient) {
        int num = inventory.get(ingredient);
        int limit = threshold.get(ingredient);
        return num < limit;     // TODO: update requests.txt
    }

    public String toString() {
        String s = "{";
        for (Ingredient ingredient : inventory.keySet()) {
            s += "( " + ingredient.getName() + ", " + inventory.get(ingredient) + " ), ";
        }
        return s.substring(0, s.length() - 2) + "}";
    }
}
