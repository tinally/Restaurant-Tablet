package edu.toronto.csc207.restaurantsolution.framework.serialization;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;
import edu.toronto.csc207.restaurantsolution.services.IngredientListService;

import java.io.IOException;

public class IngredientKeyDeserializer extends KeyDeserializer {

  private IngredientListService ingredientListService;

  IngredientKeyDeserializer(IngredientListService ingredientList) {
    this.ingredientListService = ingredientList;
  }

  @Override
  public Object deserializeKey(String key, DeserializationContext ctxt) throws IOException {
    return ingredientListService.getIngredient(key);
  }
}
