package services.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import kitchen.Ingredient;
import restaurant.MenuItem;
import services.IngredientListService;
import services.MenuItemsListService;

import java.io.IOException;

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
