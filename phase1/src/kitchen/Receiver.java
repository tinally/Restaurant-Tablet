package kitchen;

import java.util.List;

public class Receiver {
    private Inventory inventory;
    //TODO: Scan each item

    public void scanItem(Ingredient ingredient){
        inventory.addIngredient(ingredient, 20); //20 is the default value.
        //TODO: add an event?
    }

    public void ScanItems(List<Ingredient> ingredients){
        for (Ingredient ingredient: ingredients){
            scanItem(ingredient);
        }
    }
}
