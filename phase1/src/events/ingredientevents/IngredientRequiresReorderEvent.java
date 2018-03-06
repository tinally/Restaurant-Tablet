package events.ingredientevents;

import events.EventArgs;
import kitchen.Ingredient;

public class IngredientRequiresReorderEvent extends EventArgs<IngredientRequiresReorderEvent> {

  private Ingredient ingredient;

  public IngredientRequiresReorderEvent(Ingredient ingredient) {
    super(IngredientRequiresReorderEvent.class);
    this.ingredient = ingredient;
  }

  public IngredientRequiresReorderEvent() {
    super(IngredientRequiresReorderEvent.class);
  }

  public Ingredient getIngredient() {
    return ingredient;
  }

  public void setIngredient(Ingredient ingredient) {
    this.ingredient = ingredient;
  }
}
