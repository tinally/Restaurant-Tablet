package restaurant;
import kitchen.Ingredient;

import java.util.ArrayList;
import java.util.HashMap;

public class OrderItem {
    private MenuItem menuItem;
    private HashMap<Ingredient, Integer> addIngredients;
    private ArrayList<Ingredient> removedIngredients; // TODO: changed <Integer> to <Ingredient>


    public OrderItem(MenuItem menuItem, HashMap<Ingredient, Integer> add, ArrayList<Ingredient> removedIngredients){
        this.menuItem = menuItem;
        this.addIngredients = add;
        this.removedIngredients = removedIngredients;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    public void addIngredient(HashMap<Ingredient, Integer> add) {
        this.addIngredients = add;
    }

    public void removeIngredient(Ingredient unwantedIngredient){
        removedIngredients.add(unwantedIngredient);
    }

    public ArrayList<Ingredient> getRemovedIngredients() {
        return removedIngredients;
    }

    @Override
    public String toString() {
        return "This may be useful"; // TODO: toString
    }
}
