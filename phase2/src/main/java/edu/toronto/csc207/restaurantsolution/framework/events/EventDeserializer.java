package edu.toronto.csc207.restaurantsolution.framework.events;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import edu.toronto.csc207.restaurantsolution.framework.serialization.YamlDeserializerService;

import java.io.IOException;

/**
 * Jackson deserializer to deserialize arbitrary {@link EventArgs} from
 * events.txt
 */
public class EventDeserializer extends StdDeserializer<EventArgs> {

  /**
   * The YamlDeserializerService for this deserializer event.
   */
  private YamlDeserializerService yamlDeserializerService;

  /**
   * Default constructor specifying yamlDeserializerService.
   *
   * @param yamlDeserializerService the yaml deseriaizer service for this
   */
  public EventDeserializer(YamlDeserializerService yamlDeserializerService) {
    super((Class<?>) null);
    this.yamlDeserializerService = yamlDeserializerService;
  }

  /**
   * Creates an instance of EventArgs from p and ctxt.
   *
   * @param p    parsing the file
   * @param ctxt the context to be deserialized
   * @return an instance of EventArgs
   * @throws IOException             halts the prorgam
   * @throws JsonProcessingException a more general exception
   */
  @Override
  // TODO: Use in phase 2
  public EventArgs deserialize(JsonParser p, DeserializationContext ctxt)
      throws IOException, JsonProcessingException {
    JsonNode node = p.getCodec().readTree(p);
    String eventClassName = node.get("event").asText();
    try {
      Class<? extends EventArgs> eventClass =
          (Class<? extends EventArgs>)
              Class.forName("edu.toronto.csc207.restaurantsolution.framework.events.eventargs." + eventClassName);
      EventArgs object = this.yamlDeserializerService.getMapper()
          .treeToValue(node.get("payload"), eventClass);
      return object;
    } catch (ClassNotFoundException e) {
      // todo: Return default class here.
      return null;
    }

  }
}
