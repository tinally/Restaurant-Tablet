package edu.toronto.csc207.restaurantsolution.framework.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import edu.toronto.csc207.restaurantsolution.data.Ingredient;

import java.io.IOException;

public class IngredientKeySerializer extends StdSerializer<Ingredient> {

  protected IngredientKeySerializer(Class<Ingredient> t) {
    super(t);
  }

  @Override
  public void serialize(Ingredient value, JsonGenerator gen, SerializerProvider provider) throws IOException {
    gen.writeFieldName(value.getName());
  }
}
