package edu.toronto.csc207.restaurantsolution.framework.serialization;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;
import edu.toronto.csc207.restaurantsolution.data.Ingredient;
import edu.toronto.csc207.restaurantsolution.services.IngredientListService;

import java.io.IOException;

/**
 * Jackson key deserializer for representing previously loaded {@link Ingredient}
 * instances as strings in configuration files.
 * <p>
 * Simply maps a string as a key to the name of an existing Ingredient
 * from {@link IngredientListService}.
 * <p>
 * Mostly used when deserializing events.txt
 */
public class IngredientKeyDeserializer extends KeyDeserializer {
  /**
   * The ingredient list service.
   */
  private IngredientListService ingredientListService;

  /**
   * Class constructor specifying the ingredientList.
   *
   * @param ingredientList the list of ingredients
   */
  IngredientKeyDeserializer(IngredientListService ingredientList) {
    this.ingredientListService = ingredientList;
  }

  /**
   * Creates an object from key and ctxt.
   *
   * @param key  the key to be deserialized
   * @param ctxt the context to be deserialized
   * @return an Object
   * @throws IOException halts the program
   */
  @Override
  // TODO: Use in phase 2
  public Object deserializeKey(String key, DeserializationContext ctxt) throws IOException {
    return ingredientListService.getIngredient(key);
  }
}
