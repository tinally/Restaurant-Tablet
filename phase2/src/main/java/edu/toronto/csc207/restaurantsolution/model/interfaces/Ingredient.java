package edu.toronto.csc207.restaurantsolution.model.interfaces;

import java.io.Serializable;

public interface Ingredient extends Serializable {
  String getName();
  Double getCost();
  Double getPricing();
  Integer getReorderThreshold();
  Integer getDefaultReorderAmount();
}
