package edu.toronto.csc207.restaurantsolution.model.interfaces;

import java.io.Serializable;

public interface Ingredient extends Serializable {
  String getName();
  Double getCost();
  Double getPricing();
  Integer getReorderThreshold();
  Integer getDefaultReorderAmount();
  default boolean equals(Ingredient i) {
    return this.getName().equals(i.getName());
  }
}
