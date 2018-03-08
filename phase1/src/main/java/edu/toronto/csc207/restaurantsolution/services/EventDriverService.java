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
     * A list of loaded events.
     */
    private List<EventArgs> events;

    /**
     * The {@link EventEmitter} to emit the deserialized
     * events via.
     */
    private final EventEmitter emitter;

<<<<<<< HEAD:phase1/src/services/EventDriverService.java
    /**
     * ServiceConstructor to instantiate a EventDriverService
     *
     * @param resources    {@link ResourceResolverService} dependency to be
     *                     resolved by the {@link services.framework.ServiceContainer};
     * @param deserializer {@link YamlDeserializerService} dependency to be
     *                     resolved by the {@link services.framework.ServiceContainer};
     * @param emitter      {@link EventEmitter} dependency to be
     *                     resolved by the {@link services.framework.ServiceContainer};
     */
    @ServiceConstructor
    public EventDriverService(YamlDeserializerService deserializer,
                              ResourceResolverService resources,
                              EventEmitter emitter) {
        this.emitter = emitter;

        // Read all the events from file.
        ObjectMapper mapper = deserializer.getMapper();
        try {
            this.events = mapper.readValue(resources.getResource("events.yml"),
                    mapper.getTypeFactory().constructCollectionType(List.class, EventArgs.class));
        } catch (IOException e) {

            // If something happens, then we will just return an empty list.
            this.events = new ArrayList<>();
        }
=======
  /**
   * ServiceConstructor to instantiate a EventDriverService
   * @param resources {@link ResourceResolverService} dependency to be
   *                  resolved by the {@link ServiceContainer};
   * @param deserializer {@link YamlDeserializerService} dependency to be
   *                  resolved by the {@link ServiceContainer};
   * @param emitter {@link EventEmitter} dependency to be
   *                  resolved by the {@link ServiceContainer};
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
>>>>>>> 6c2ba67e6d43c990817f7455d710287af5146462:phase1/src/main/java/edu/toronto/csc207/restaurantsolution/services/EventDriverService.java
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
