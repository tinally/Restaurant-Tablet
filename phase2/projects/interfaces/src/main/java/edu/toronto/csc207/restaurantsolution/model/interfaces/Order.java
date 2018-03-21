package edu.toronto.csc207.restaurantsolution.model.interfaces;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface Order {
  UUID getOrderID();
  Integer getTableNumber();
  Integer getOrderNumber();
  MenuItem getMenuItem();
  List<Ingredient> getRemovals();
  Map<Ingredient, Integer> getAdditions();
  Double getOrderCost();
  Instant getOrderTimestamp();
  String getCreatingUser();
}
