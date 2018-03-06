package services.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import events.EventArgs;
import services.serialization.YamlDeserializerService;

import java.io.IOException;

import static java.lang.Class.forName;

public class EventDeserializer extends StdDeserializer<EventArgs> {

  private YamlDeserializerService yamlDeserializerService;

  public EventDeserializer(YamlDeserializerService yamlDeserializerService) {
    super((Class<?>) null);
    this.yamlDeserializerService = yamlDeserializerService;
  }

  private EventDeserializer(Class<?> vc) {
    super(vc);
  }

  @Override
  public EventArgs deserialize(JsonParser p, DeserializationContext ctxt)
      throws IOException, JsonProcessingException {
    JsonNode node = p.getCodec().readTree(p);
    String eventClassName = node.get("event").asText();
    try {
      Class<? extends EventArgs> eventClass =
          (Class<? extends EventArgs>) Class.forName("events.eventtypes." + eventClassName);
      EventArgs object = this.yamlDeserializerService.getMapper()
          .treeToValue(node.get("payload"), eventClass);
      return object;
    } catch (ClassNotFoundException e) {
      // todo: Return default class here.
      return null;
    }

  }
}
