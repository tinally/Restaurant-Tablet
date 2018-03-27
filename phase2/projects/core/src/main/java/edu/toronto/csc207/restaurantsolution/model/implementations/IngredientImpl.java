package edu.toronto.csc207.restaurantsolution.model.implementations;

import edu.toronto.csc207.restaurantsolution.model.interfaces.Ingredient;

/**
 * Represents an implementation of Ingredient.
 */
public final class IngredientImpl implements Ingredient {

  public IngredientImpl() {

  }

  public IngredientImpl(String name, Double cost, Double pricing, Integer reorderThreshold) {
    this.setName(name);
    this.setCost(cost);
    this.setPricing(pricing);
    this.setReorderThreshold(reorderThreshold);
    this.setDefaultReorderAmount(20);
  }

  private String name;

  private Double cost;

  private Double pricing;

  private Integer reorderThreshold;

  private Integer defaultReorderAmount;

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public Double getCost() {
    return this.cost;
  }

  @Override
  public Double getPricing() {
    return this.pricing;
  }

  @Override
  public Integer getReorderThreshold() {
    return this.reorderThreshold;
  }

  @Override
  public Integer getDefaultReorderAmount() {
    return this.defaultReorderAmount;
  }

  @Override
  public boolean equals(Object o) {
    return o instanceof Ingredient && this.equals((Ingredient) o);
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setCost(Double cost) {
    this.cost = cost;
  }

  public void setPricing(Double pricing) {
    this.pricing = pricing;
  }

  public void setReorderThreshold(Integer reorderThreshold) {
    this.reorderThreshold = reorderThreshold;
  }

  public void setDefaultReorderAmount(Integer defaultReorderAmount) {
    this.defaultReorderAmount = defaultReorderAmount;
  }

  @Override
  public String toString() {
    return this.getName();
  }
}
