package edu.toronto.csc207.restaurantsolution.model.interfaces;

import java.io.Serializable;
import java.util.Map;

/**
 * Represents a MenuItem.
 */
public interface MenuItem extends Serializable {
    /**
     * Returns name of the MenuItem.
     *
     * @return name of the MenuItem
     */
    String getName();

    /**
     * Returns price of the MenuItem.
     *
     * @return price of the MenuItem
     */
    Double getPrice();

    /**
     * Returns the map of Ingredient needed with the corresponding amount.
     *
     * @return map of Ingredient and Integer
     */
    Map<Ingredient, Integer> getIngredientRequirements();

  /**
   * Sets the required ingredients of this menu item.
   *
   * @param ingredientRequirements a map of the required ingredients.
   */
  void setIngredientRequirements(Map<Ingredient, Integer> ingredientRequirements);

    /**
     * Checks whether m is the same MenuItem as this.
     *
     * @param m the MenuItem to be compared with this
     * @return true if m and this are the same; false otherwise
     */
    default boolean equals(MenuItem m) {
        return this.getName().equals(m.getName());
    }
}
