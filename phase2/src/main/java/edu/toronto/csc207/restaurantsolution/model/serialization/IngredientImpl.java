package edu.toronto.csc207.restaurantsolution.model.serialization;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import edu.toronto.csc207.restaurantsolution.model.interfaces.Ingredient;

import java.io.Serializable;

@DatabaseTable(tableName = "ingredients")
public final class IngredientImpl implements Ingredient {

  @DatabaseField(id = true)
  private String name;

  @DatabaseField
  private Double cost;

  @DatabaseField
  private Double pricing;

  @DatabaseField
  private Integer reorderThreshold;

  @DatabaseField
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
}
