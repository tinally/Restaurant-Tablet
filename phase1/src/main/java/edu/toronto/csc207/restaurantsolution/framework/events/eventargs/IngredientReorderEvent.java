package edu.toronto.csc207.restaurantsolution.framework.events.eventargs;

import edu.toronto.csc207.restaurantsolution.framework.events.EventArgs;
import edu.toronto.csc207.restaurantsolution.data.Ingredient;

/**
<<<<<<< HEAD:phase1/src/events/newevents/IngredientReorderEvent.java
 * IngredientReorderEvent represents the event for reordering the ingredient.
 */
public class IngredientReorderEvent extends EventArgs<IngredientReorderEvent> {

    /**
     * The ingredient to be reordered.
     */
    private Ingredient ingredient;

    /**
     * Class constructor specifying the ingredient to be reordered.
     *
     * @param ingredient ingredient to be reordered
     */
    public IngredientReorderEvent(Ingredient ingredient) {
        super(IngredientReorderEvent.class);
        this.ingredient = ingredient;
    }

    /**
     * Default constructor of IngredientReorderEvent.
     */
    public IngredientReorderEvent() {
        super(IngredientReorderEvent.class);
    }

    /**
     * Returns the ingredient to be reordered.
     *
     * @return the ingredient to be reordered
     */
    public Ingredient getIngredient() {
        return ingredient;
    }

    /**
     * Sets the ingredient to be reordered.
     *
     * @param ingredient to be reordered
     */
    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }
=======
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
>>>>>>> 6c2ba67e6d43c990817f7455d710287af5146462:phase1/src/main/java/edu/toronto/csc207/restaurantsolution/framework/events/eventargs/IngredientReorderEvent.java

}
