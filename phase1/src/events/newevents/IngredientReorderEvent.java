package events.newevents;

import events.EventArgs;
import kitchen.Ingredient;

/**
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

}