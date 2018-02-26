package restaurant;

import java.util.HashMap;
import kitchen.Ingredient;

public class MenuItem {
    private HashMap<Ingredient, Integer> ingredients;
    private int price;


    public MenuItem(HashMap<Ingredient, Integer> ingredients, int price){
        this.price = price;
        this.ingredients = ingredients;
    }

    public int getPrice() {
        return price;
    }

    public HashMap<Ingredient, Integer> getIngredients() {
        return ingredients;
    }

    public void setIngredients(HashMap<Ingredient, Integer> ingredients) {
        this.ingredients = ingredients;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "This may be useful"; //TODO: May be useful
    }
}
