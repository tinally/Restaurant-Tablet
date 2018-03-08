package edu.toronto.csc207.restaurantsolution.framework.serialization;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;
import edu.toronto.csc207.restaurantsolution.data.Ingredient;
import edu.toronto.csc207.restaurantsolution.services.IngredientListService;

import java.io.IOException;

/**
 * Jackson key deserializer for representing previously loaded {@link Ingredient}
 * instances as strings in configuration files.
 *
 * Simply maps a string as a key to the name of an existing Ingredient
 * from {@link IngredientListService}.
 *
 * Mostly used when deserializing events.txt
 */
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
