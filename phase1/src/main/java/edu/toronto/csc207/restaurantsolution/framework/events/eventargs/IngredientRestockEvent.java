package edu.toronto.csc207.restaurantsolution.framework.events.eventargs;

import edu.toronto.csc207.restaurantsolution.framework.events.EventArgs;
import edu.toronto.csc207.restaurantsolution.data.Ingredient;

<<<<<<< HEAD:phase1/src/events/newevents/IngredientRestockEvent.java
/**
 * IngredientRestockEvent represents the event for restocking the ingredient.
=======

/**
 * An event to trigger a restock of an ingredient.
>>>>>>> 6c2ba67e6d43c990817f7455d710287af5146462:phase1/src/main/java/edu/toronto/csc207/restaurantsolution/framework/events/eventargs/IngredientRestockEvent.java
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
