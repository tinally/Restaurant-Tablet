package kitchen;

/**
 * Ingredient represents the ingredient of OrderItems.
 */
public class Ingredient {

    /**
     * The name of the ingredient.
     */
    private String name;

    /**
     * The cost of the ingredient in dollars.
     */
    private Double cost;

    /**
     * Class constructor specifying the name and cost of this ingredient.
     * @param name  name of the ingredient
     * @param cost  cost of the ingredient in dollars
     */
    public Ingredient(String name, Double cost) {
        this.name = name;
        this.cost = cost;
    }

    /**
     * Finds the name of this ingredient.
     * @return the name of the ingredient
     */
    public String getName() {
        return this.name;
    }

    /**
     * Finds the cost of this ingredient.
     * @return
     */
    public Double getCost() {
        return this.cost;
    }
}