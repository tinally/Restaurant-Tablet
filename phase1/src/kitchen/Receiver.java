package kitchen;

import java.util.List;

public class Receiver {
    private Inventory inventory;

    public void scanItem(Ingredient ingredient){
        inventory.addToInventory(ingredient, ingredient.getReorderAmount());
    }

    public void ScanItems(List<Ingredient> ingredients){
        for (Ingredient ingredient: ingredients){
            scanItem(ingredient);
        }
    }
}
