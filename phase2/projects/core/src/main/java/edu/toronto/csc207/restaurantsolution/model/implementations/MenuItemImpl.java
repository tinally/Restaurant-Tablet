package edu.toronto.csc207.restaurantsolution.model.implementations;

import edu.toronto.csc207.restaurantsolution.model.interfaces.Ingredient;
import edu.toronto.csc207.restaurantsolution.model.interfaces.MenuItem;

import java.util.Map;

public class MenuItemImpl implements MenuItem {

  public MenuItemImpl() {

  }

  public MenuItemImpl(String name, Map<Ingredient, Integer> ingredients, double price) {
    this.setName(name);
    this.setIngredientRequirements(ingredients);
    this.setPrice(price);
  }

  private String name;
  private Double price;
  private Map<Ingredient,Integer> ingredientRequirements;

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public Double getPrice() {
    return this.price;
  }

  @Override
  public Map<Ingredient, Integer> getIngredientRequirements() {
    return this.ingredientRequirements;
  }

  public void setIngredientRequirements(Map<Ingredient, Integer> ingredientRequirements) {
    this.ingredientRequirements = ingredientRequirements;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  @Override
  public boolean equals(Object o) {
    return o instanceof MenuItem && this.equals((MenuItem) o);
  }
}