package edu.toronto.csc207.restaurantsolution.framework.events.eventargs;

import edu.toronto.csc207.restaurantsolution.data.Ingredient;
import edu.toronto.csc207.restaurantsolution.framework.events.EventArgs;

/**
 * IngredientRestockEvent represents the event for restocking the ingredient.
 */
public class IngredientRestockEvent extends EventArgs<IngredientRestockEvent> {
  /**
   * The ingredient to be reordered.
   */
  private Ingredient ingredient;

  /**
   * Class constructor specifying the ingredient and restockedAmount.
   *
   * @param ingredient      the ingredient to be restocked
   * @param restockedAmount the amount for restock
   */
  // TODO: Use restockedAmount in phase 2
  public IngredientRestockEvent(Ingredient ingredient, int restockedAmount) {
    super(IngredientRestockEvent.class);
    this.ingredient = ingredient;
  }

  /**
   * Default constructor for IngredientRestockEvent.
   */
  public IngredientRestockEvent() {
    super(IngredientRestockEvent.class);
  }

  /**
   * Returns the ingredient to be restocked.
   *
   * @return the ingredient to be restocked
   */
  public Ingredient getIngredient() {
    return ingredient;
  }

  /**
   * Sets the ingredient to be restocked.
   *
   * @param ingredient the ingredient to be restocked
   */
  public void setIngredient(Ingredient ingredient) {
    this.ingredient = ingredient;
  }
}
