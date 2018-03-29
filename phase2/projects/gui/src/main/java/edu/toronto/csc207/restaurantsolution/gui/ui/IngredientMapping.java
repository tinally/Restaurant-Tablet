package edu.toronto.csc207.restaurantsolution.gui.ui;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import edu.toronto.csc207.restaurantsolution.model.interfaces.Ingredient;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class IngredientMapping extends RecursiveTreeObject<IngredientMapping> {
  private final ObjectProperty<Ingredient> ingredient;
  private final IntegerProperty quantity;

  IngredientMapping(Ingredient ingredient, Integer quantity) {
    this.ingredient = new SimpleObjectProperty<>(ingredient);
    this.quantity = new SimpleIntegerProperty(quantity);
  }

  public ObjectProperty<Ingredient> ingredientProperty() {
    return ingredient;
  }

  public IntegerProperty quantityProperty() {
    return quantity;
  }
}