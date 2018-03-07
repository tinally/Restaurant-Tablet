package events.newevents;

import events.EventArgs;
import kitchen.Ingredient;

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
