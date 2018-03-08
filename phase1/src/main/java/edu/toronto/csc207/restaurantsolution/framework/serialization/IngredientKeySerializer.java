package edu.toronto.csc207.restaurantsolution.framework.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import edu.toronto.csc207.restaurantsolution.data.Ingredient;

import java.io.IOException;

/**
 * Jackson serializer for representing {@link Ingredient}
 * instances as strings in configuration files.
 * <p>
 * Simply maps an Ingredient instance to its name.
 * <p>
 * Used when outputting configuration files to console or disk.
 */
public class IngredientKeySerializer extends StdSerializer<Ingredient> {
  protected IngredientKeySerializer(Class<Ingredient> t) {
    super(t);
  }

  @Override
  public void serialize(Ingredient value, JsonGenerator gen, SerializerProvider provider) throws IOException {
    gen.writeFieldName(value.getName());
  }
}
