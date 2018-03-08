package edu.toronto.csc207.restaurantsolution.framework.events.eventargs;

import edu.toronto.csc207.restaurantsolution.framework.events.EventArgs;
import edu.toronto.csc207.restaurantsolution.data.Ingredient;

public class IngredientRestockEvent extends EventArgs<IngredientRestockEvent> {

  private Ingredient ingredient;

  public IngredientRestockEvent(Ingredient ingredient, int restockedAmount) {
    super(IngredientRestockEvent.class);
    this.ingredient = ingredient;
  }

  public IngredientRestockEvent() {
    super(IngredientRestockEvent.class);
  }

  public Ingredient getIngredient() {
    return ingredient;
  }

  public void setIngredient(Ingredient ingredient) {
    this.ingredient = ingredient;
  }

}
