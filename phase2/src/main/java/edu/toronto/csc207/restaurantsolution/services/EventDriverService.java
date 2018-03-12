package edu.toronto.csc207.restaurantsolution.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.toronto.csc207.restaurantsolution.framework.events.EventArgs;
import edu.toronto.csc207.restaurantsolution.framework.events.EventEmitter;
import edu.toronto.csc207.restaurantsolution.framework.serialization.YamlDeserializerService;
import edu.toronto.csc207.restaurantsolution.framework.services.Service;
import edu.toronto.csc207.restaurantsolution.framework.services.ServiceConstructor;
import edu.toronto.csc207.restaurantsolution.framework.services.ServiceContainer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A {@link Service} that reads events from event.txt, and
 * fires each event in order of declaration.
 */
public class EventDriverService extends Service implements Runnable {
  /**
   * The {@link EventEmitter} to emit the deserialized events.
   */
  private final EventEmitter emitter;
  /**
   * A list of loaded events.
   */
  private List<EventArgs> events;

  /**
   * ServiceConstructor to instantiate a EventDriverService
   *
   * @param resources    {@link ResourceResolverService} dependency to be
   *                     resolved by the {@link ServiceContainer};
   * @param deserializer {@link YamlDeserializerService} dependency to be
   *                     resolved by the {@link ServiceContainer};
   * @param emitter      {@link EventEmitter} dependency to be
   *                     resolved by the {@link ServiceContainer};
   */
  @ServiceConstructor
  public EventDriverService(YamlDeserializerService deserializer,
                            ResourceResolverService resources,
                            EventEmitter emitter) {
    this.emitter = emitter;

    // Read all the events from file.
    ObjectMapper mapper = deserializer.getMapper();
    try {
      this.events = mapper.readValue(resources.getResource("events.txt"),
          mapper.getTypeFactory().constructCollectionType(List.class, EventArgs.class));
    } catch (IOException e) {
      e.printStackTrace();
      // If something happens, then we will just return an empty list.
      this.events = new ArrayList<>();
    }
  }

  /**
   * Execute the events that were loaded from events.txt in order.
   */
  public void run() {
    for (EventArgs e : events) {
      emitter.onEvent(e);
    }
  }
}
