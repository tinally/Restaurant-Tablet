package restaurant;

import java.util.HashMap;
import kitchen.Ingredient;

public class MenuItem {
    private HashMap<Ingredient, Integer> ingredients;
    private int price;
    private double discount; //Discounted price

// TODO: Add discount.
    public MenuItem(HashMap<Ingredient, Integer> ingredients, int price){
        this.price = price;
        this.ingredients = ingredients;
        this.discount = 1.0;
    }

    public int getOriginalPrice() {
        return price;
    }

    public void setDiscount(double discount) {
        this.discount -= discount;
    }

    public double getDiscountedPrice(){
        return discount * price; // TODO: added DiscountedPrice
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
