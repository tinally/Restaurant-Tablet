package edu.toronto.csc207.restaurantsolution.framework.events.eventargs;

import edu.toronto.csc207.restaurantsolution.framework.events.EventArgs;
import edu.toronto.csc207.restaurantsolution.data.Ingredient;

/**
 * An event to trigger reordering an ingredient.
 */
public class IngredientReorderEvent extends EventArgs<IngredientReorderEvent> {

  /**
   * The ingredient to reorder.
   */
  private Ingredient ingredient;

  public IngredientReorderEvent(Ingredient ingredient) {
    super(IngredientReorderEvent.class);
    this.ingredient = ingredient;
  }

  public IngredientReorderEvent() {
    super(IngredientReorderEvent.class);
  }

  public Ingredient getIngredient() {
    return ingredient;
  }

  public void setIngredient(Ingredient ingredient) {
    this.ingredient = ingredient;
  }

}
