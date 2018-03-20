package edu.toronto.csc207.restaurantsolution.model.serialization;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import edu.toronto.csc207.restaurantsolution.model.interfaces.Ingredient;
import edu.toronto.csc207.restaurantsolution.model.interfaces.MenuItem;

import java.util.Map;

@DatabaseTable()
public class MenuItemImpl implements MenuItem {

  @DatabaseField()
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
}
