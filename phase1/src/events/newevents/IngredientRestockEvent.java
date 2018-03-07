package events.newevents;

import events.EventArgs;
import kitchen.Ingredient;

public class IngredientRestockEvent extends EventArgs<IngredientRestockEvent> {

  private Ingredient ingredient;
  private int restockedAmount;

  public IngredientRestockEvent(Ingredient ingredient, int restockedAmount) {
    super(IngredientRestockEvent.class);
    this.ingredient = ingredient;
    this.restockedAmount = restockedAmount;
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

  public int getRestockedAmount() {
    return restockedAmount;
  }

  public void setRestockedAmount(int restockedAmount) {
    this.restockedAmount = restockedAmount;
  }
}
