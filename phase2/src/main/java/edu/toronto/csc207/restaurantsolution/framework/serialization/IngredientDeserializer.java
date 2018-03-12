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
 * <p>
 * Simply maps a string as a key to the name of an existing Ingredient
 * from {@link IngredientListService}.
 * <p>
 * Mostly used when deserializing events.txt
 */
public class IngredientDeserializer extends StdDeserializer<Ingredient> {

  /**
   * The list of services with ingredients.
   **/
  private IngredientListService ingredientListService;

  /**
   * Class constructor specifying ingredientList.
   *
   * @param ingredientList the list of services with ingredients
   */
  IngredientDeserializer(IngredientListService ingredientList) {
    this((Class<?>) null);
    this.ingredientListService = ingredientList;
  }

  /**
   * Default constructor.
   *
   * @param vc the class to deserialize as
   */
  private IngredientDeserializer(Class<?> vc) {
    super(vc);
  }

  /**
   * Creates an instance of Ingredient from p and ctxt.
   *
   * @param p    parsing the file
   * @param ctxt context to be deserialized
   * @return an instance of Ingredient
   * @throws IOException             halts the program
   * @throws JsonProcessingException a more general exception
   */
  @Override
  public Ingredient deserialize(JsonParser p, DeserializationContext ctxt)
      throws IOException, JsonProcessingException {
    String ingredientName = p.getValueAsString();
    return ingredientListService.getIngredient(ingredientName);
  }
}
