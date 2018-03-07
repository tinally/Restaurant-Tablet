package events.newevents;

import events.EventArgs;
import kitchen.Ingredient;

public class IngredientReceivedEvent extends EventArgs<IngredientReceivedEvent> {

  private Ingredient ingredient;
  private int restockedAmount;

  public IngredientReceivedEvent(Ingredient ingredient, int restockedAmount) {
    super(IngredientReceivedEvent.class);
    this.ingredient = ingredient;
    this.restockedAmount = restockedAmount;
  }

  public IngredientReceivedEvent() {
    super(IngredientReceivedEvent.class);
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
