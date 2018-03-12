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

  /**
   * Default constructor.
   *
   * @param t the class to serialize as
   */
  IngredientKeySerializer(Class<Ingredient> t) {
    super(t);
  }

  /**
   * Writes the file from value, gen and provider.
   *
   * @param value    the value to be written in file
   * @param gen      generator
   * @param provider provides the serialization
   * @throws IOException halts the program
   */
  @Override
  public void serialize(Ingredient value, JsonGenerator gen, SerializerProvider provider) throws IOException {
    gen.writeFieldName(value.getName());
  }
}
