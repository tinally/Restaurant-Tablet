package edu.toronto.csc207.restaurantsolution.model.interfaces;

import java.io.Serializable;
import java.util.Map;

public interface MenuItem extends Serializable {
  String getName();
  Double getPrice();
  Map<Ingredient, Integer> getIngredientRequirements();
}
