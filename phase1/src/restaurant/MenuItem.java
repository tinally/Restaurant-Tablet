package restaurant;

import java.util.Map;
import kitchen.Ingredient;

/**
 * MenuItem represents the item in the menu
 */
public class MenuItem {

    /**
     * The name of the menuItem to be shown on the menu
     */
    private String name;
    /**
     * A Map storing how much Ingredient is needed
     */
    private Map<Ingredient, Integer> ingredients;
    /**
     * The price of the item
     */
    private double price;
    /**
     * A discount to be applied. If there is no discount, then the default value is 1
     */
    private double discount; //Discounted price

    public MenuItem(String name, Map<Ingredient, Integer> ingredients, double price){
        this.name = name;
        this.price = price;
        this.ingredients = ingredients;
        this.discount = 1.0;
    }

    /**
     *
     * @return the name of this menuItem
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return The original price of the item
     */
    public double getOriginalPrice() {
        return price;
    }

    /**
     * Discount is the adjustment factor to be set. For example for 30% off you would
     * need to put 0.3 as discount
     * @param discount The discount to be set
     */
    public void setDiscount(double discount) {
        this.discount -= discount;
    }

    /**
     *
     * @return the price of the item with the discount applied
     */
    public double getPrice(){
        return discount * price; // TODO: added DiscountedPrice
    }

    /**
     *
     * @return the Map containing how many ingredients the item needs
     */
    public Map<Ingredient, Integer> getIngredients() {
        return ingredients;
    }

    /**
     *
     * @param ingredients The ingredients to be set
     */
    public void setIngredients(Map<Ingredient, Integer> ingredients) {
        this.ingredients = ingredients;
    }

    /**
     *
     * @param price the original price to be set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Increase the price by this factor
     * @param factor the factor by which the price is increased.
     */
    public void increasePrice(double factor){
        this.price += price;
    }

    @Override
    public String toString() {
        return "This may be useful"; //TODO: May be useful
    }
}
