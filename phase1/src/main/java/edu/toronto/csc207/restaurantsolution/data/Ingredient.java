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
  /**
   * The threshold at which to reorder this ingredient.
   */
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

  /**
   * Returns the threshold of this Ingredient when it requires reordering.
   *
   * @return the threshold of this
   */
  public Integer getReorderThreshold() {
    return threshold;
  }

  /**
   * Returns a string representation of this Ingredient.
   *
   * @return a string representation of this
   */
  @Override
  public String toString() {
    return this.getName();
  }

  /**
   * Returns the amount that is reordered for this Ingredient.
   *
   * @return The reorderAmount of this
   */
  public int getReorderAmount() {
    return reorderAmount;
  }

  /**
   * Changes the reorderAmount of the ingredient.
   *
   * @param reorderAmount the reorderAmount to be changed instead
   */
  public void changeReorderAmount(int reorderAmount) {
    this.reorderAmount = reorderAmount;
  }
}
