package services.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdKeyDeserializer;
import kitchen.Ingredient;
import services.IngredientListService;

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
