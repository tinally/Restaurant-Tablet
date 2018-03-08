package edu.toronto.csc207.restaurantsolution.framework.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import edu.toronto.csc207.restaurantsolution.data.Ingredient;
import edu.toronto.csc207.restaurantsolution.services.IngredientListService;

import java.io.IOException;

/**
 * Jackson deserializer for representing previously loaded {@link Ingredient}
 * instances as strings in configuration files.
 *
 * Simply maps a string as a key to the name of an existing Ingredient
 * from {@link IngredientListService}.
 *
 * Mostly used when deserializing events.txt
 */
public class IngredientDeserializer extends StdDeserializer<Ingredient> {

  private IngredientListService ingredientListService;

  IngredientDeserializer(IngredientListService ingredientList) {
    this((Class<?>) null);
    this.ingredientListService = ingredientList;
  }

  private IngredientDeserializer(Class<?> vc) {
    super(vc);
  }

  @Override
  public Ingredient deserialize(JsonParser p, DeserializationContext ctxt)
      throws IOException, JsonProcessingException {
    String ingredientName = p.getValueAsString();
    return ingredientListService.getIngredient(ingredientName);
  }
}
