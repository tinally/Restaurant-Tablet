package edu.toronto.csc207.restaurantsolution.data;

import java.util.Map;

/**
 * MenuItem represents the item in the menu.
 * This class is serializable to JSON or YAML.
 */
public class MenuItem {
  /**
   * The name of the menuItem to be shown on the menu.
   */
  private String name;
  /**
   * A map storing how much Ingredient is needed.
   */
  private Map<Ingredient, Integer> ingredients;
  /**
   * The price of the item.
   */
  private double price;
  /**
   * A discount to be applied.
   */
  private double discount;

  /**
   * Class constructor specifying name, ingredients and price.
   *
   * @param name        the name of this MenuItem
   * @param ingredients the ingredients needed for this MenuItem
   * @param price       the price of this MenuItem
   */
  public MenuItem(String name, Map<Ingredient, Integer> ingredients, double price) {
    this.name = name;
    this.price = price;
    this.ingredients = ingredients;
    this.discount = 1.0;
  }

  /**
   * Returns the name of this MenuItem.
   *
   * @return the name of this MenuItem
   */
  public String getName() {
    return name;
  }

  /**
   * Returns the original price of this MenuItem.
   *
   * @return The original price of this MenuItem
   */
  // TODO: Use in phase 2
  public double getOriginalPrice() {
    return price;
  }

  /**
   * Discount is the adjustment factor to be set. For example for 30% off you would
   * need to put 0.3 as discount
   *
   * @param discount The discount to be set
   */
  // TODO: Use in phase 2
  public void setDiscount(double discount) {
    this.discount -= discount;
  }

  /**
   * Returns the price after discount.
   *
   * @return the price of the item with the discount applied
   */
  public double getPrice() {
    return discount * price;
  }

  /**
   * Sets the price of this MenuItem.
   *
   * @param price the price to be set
   */
  // TODO: Use in phase 2
  public void setPrice(double price) {
    this.price = price;
  }

  /**
   * Returns the ingredients needed for this MenuItem.
   *
   * @return the Map containing how many ingredients the item needs
   */
  public Map<Ingredient, Integer> getIngredients() {
    return ingredients;
  }

  /**
   * Sets the ingredients needed for this MenuItem.
   *
   * @param ingredients The ingredients to be set
   */
  public void setIngredients(Map<Ingredient, Integer> ingredients) {
    this.ingredients = ingredients;
  }

  /**
   * Increases the price of this menu item.
   *
   * @param addedPrice the amount by which to increase the price.
   */
  public void increasePrice(double addedPrice) {
    this.price += addedPrice;
  }

  /**
   * Returns the string representation of this MenuItem.
   *
   * @return the string representation of this MenuItem
   */
  @Override
  public String toString() {
    return this.getName();
  }
}
