package edu.toronto.csc207.restaurantsolution.framework.events.eventargs;

import edu.toronto.csc207.restaurantsolution.framework.events.EventArgs;
import edu.toronto.csc207.restaurantsolution.data.Ingredient;

public class IngredientReorderEvent extends EventArgs<IngredientReorderEvent> {

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
