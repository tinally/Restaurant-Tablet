package kitchen;

import java.util.List;

/**
 * Receiver represents the receiver and scanner of the restocked ingredients upon requests.txt.
 */
public class Receiver {

    /**
     * The current inventory of this restaurant.
     */
    private Inventory inventory;

    /**
     * Scans the ingredient coming into the restaurant.
     *
     * @param ingredient ingredient to be scanned
     */
    private void scanItem(Ingredient ingredient) {
        inventory.addToInventory(ingredient, ingredient.getReorderAmount());
    }

    /**
     * Scans all the ingredients coming into the restaurant.
     *
     * @param ingredients all the ingredients to be scanned
     */
    public void ScanItems(List<Ingredient> ingredients) {
        for (Ingredient ingredient : ingredients) {
            scanItem(ingredient);
        }
    }
}
