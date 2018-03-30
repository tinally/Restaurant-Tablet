package edu.toronto.csc207.restaurantsolution.model.implementations;

import edu.toronto.csc207.restaurantsolution.model.interfaces.Ingredient;
import edu.toronto.csc207.restaurantsolution.model.interfaces.MenuItem;

import java.util.Map;

/**
 * Represents an implementation of MenuItem.
 */
public class MenuItemImpl implements MenuItem {
  private String name;
  private Double price;
  private Map<Ingredient, Integer> ingredientRequirements;

  public MenuItemImpl() {
  }

  public MenuItemImpl(String name, Map<Ingredient, Integer> ingredients, double price) {
    this.setName(name);
    this.setIngredientRequirements(ingredients);
    this.setPrice(price);
  }

  @Override
  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public Double getPrice() {
    return this.price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  @Override
  public Map<Ingredient, Integer> getIngredientRequirements() {
    return this.ingredientRequirements;
  }

  @Override
  public void setIngredientRequirements(Map<Ingredient, Integer> ingredientRequirements) {
    this.ingredientRequirements = ingredientRequirements;
  }

  @Override
  public boolean equals(Object o) {
    return o instanceof MenuItem && this.equals((MenuItem) o);
  }

  @Override
  public String toString() {
    return this.getName();
  }
}
