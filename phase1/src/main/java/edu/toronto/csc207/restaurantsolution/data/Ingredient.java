package edu.toronto.csc207.restaurantsolution.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Ingredient represents the ingredient of Order in this restaurant.
 * This class is serializable to JSON or YAML.
 */
public class Ingredient implements Serializable {
  /**
   * The name of the ingredient.
   */
  private String name;

  /**
   * The cost of the ingredient in dollars.
   */
  private Double cost;

  /**
   * The price of each unit of the ingredient.
   */
  private Double pricing;

  private Integer threshold;

  /**
   * Amount to be reordered when stock is low. It is 20 by default.
   */
  private int reorderAmount = 20;

  /**
   * Class constructor specifying the name and cost of this ingredient.
   *
   * @param name             name of the ingredient
   * @param cost             cost of the ingredient in dollars
   * @param reorderThreshold the threshold to trigger a reorder of this item.
   */
  public Ingredient(
      @JsonProperty("name") String name,
      @JsonProperty("cost") Double cost,
      @JsonProperty("pricing") Double pricing,
      @JsonProperty("threshold") Integer reorderThreshold) {
    this.name = name;
    this.cost = cost;
    this.pricing = pricing;
    this.threshold = reorderThreshold;
  }

  /**
   * Returns the name of this ingredient.
   *
   * @return the name of the ingredient
   */
  public String getName() {
    return this.name;
  }

  /**
   * Returns the cost of this ingredient.
   *
   * @return the cost of the ingredient.
   */
  public Double getCost() {
    return this.cost;
  }

  /**
   * Returns the price of this ingredient.
   *
   * @return the pricing of the ingredient.
   */
  public Double getPricing() {
    return this.pricing;
  }

  public Integer getReorderThreshold() {
    return threshold;
  }

  @Override
  public String toString() {
    return this.getName();
  }

  /**
   * @return The reorderAmount for the ingredient
   */
  public int getReorderAmount() {
    return reorderAmount;
  }

  /**
   * Change the reorderAmount of the ingredient.
   *
   * @param reorderAmount the reorderAmount to be changed instead
   */
  public void changeReorderAmount(int reorderAmount) {
    this.reorderAmount = reorderAmount;
  }
}
