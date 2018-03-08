package edu.toronto.csc207.restaurantsolution.framework.events;

import edu.toronto.csc207.restaurantsolution.framework.services.Service;
import edu.toronto.csc207.restaurantsolution.framework.services.ServiceConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The EventEmitter is responsible for broadcasting events to registered event handlers.
 */
public class EventEmitter extends Service {

<<<<<<< HEAD:phase1/src/events/EventEmitter.java
    private Map<Class<? extends EventArgs>, List<RestaurantEventHandler<? extends EventArgs>>> eventHandlers;

    @ServiceConstructor
    public EventEmitter() {
        this.eventHandlers = new HashMap<>();
    }
=======
  /**
   * A map to store event handlers.
   */
  private Map<Class<? extends EventArgs>, List<RestaurantEventHandler<? extends EventArgs>>> eventHandlers;

  /**
   * Class constructor for an EventEmitter.
   */
  @ServiceConstructor
  public EventEmitter() {
    this.eventHandlers = new HashMap<>();
  }
>>>>>>> 6c2ba67e6d43c990817f7455d710287af5146462:phase1/src/main/java/edu/toronto/csc207/restaurantsolution/framework/events/EventEmitter.java

    /**
     * Registers an event handler for a given event.
     * Every time an event is fired, the even handler will run.
     *
     * @param eventHandler The event handler to run.
     * @param event        The class of the event to run.
     * @param <T>          The type of the event to register.
     * @see EventArgs
     */
    public <T extends EventArgs> void registerEventHandler(RestaurantEventHandler<T> eventHandler,
                                                           Class<T> event) {
        this.eventHandlers.putIfAbsent(event, new ArrayList<>());
        this.eventHandlers.get(event).add(eventHandler);
    }

    /**
     * Removes an event handler for a given event.
     * Every time an event is fired, the even handler will run.
     *
     * @param eventHandler The event handler to run.
     * @param event        The class of the event to run.
     * @param <T>          The type of the event to register.
     * @see EventArgs
     */
    public <T extends EventArgs> void removeEventHandler(RestaurantEventHandler<T> eventHandler,
                                                         Class<T> event) {
        this.eventHandlers.putIfAbsent(event, new ArrayList<>());
        this.eventHandlers.get(event).remove(eventHandler);
    }

<<<<<<< HEAD:phase1/src/events/EventEmitter.java
    /**
     * Fires the given event from the given sender, making all registered event handlers run.
     *
     * @param eventArgs The arguments of the event to fire.
     * @param <T>       The type of the event to fire.
     */
    @SuppressWarnings("unchecked")
    public <T extends EventArgs> void onEvent(T eventArgs) {
        List<RestaurantEventHandler<? extends EventArgs>> eventHandlers
                = this.eventHandlers.get(eventArgs.getEventClass());
        if (eventHandlers == null) return;
        for (RestaurantEventHandler<?> eventHandler : eventHandlers) {
            if (!eventArgs.isCancelled() && eventHandler != null) {
                ((RestaurantEventHandler<T>) eventHandler).handle(eventArgs);
            }
        }
=======
  /**
   * Fires the given event from the given sender, making all registered event handlers run.
   * @param eventArgs The arguments of the event to fire.
   * @param <T> The type of the event to fire.
   */
  @SuppressWarnings("unchecked")
  public <T extends EventArgs> void onEvent(T eventArgs) {
    List<RestaurantEventHandler<? extends EventArgs>> eventHandlers
        = this.eventHandlers.get(eventArgs.getEventClass());
    if (eventHandlers == null) return;
    for (RestaurantEventHandler<?> eventHandler : eventHandlers) {
      if (eventHandler != null) {
        ((RestaurantEventHandler<T>) eventHandler).handle(eventArgs);
      }
>>>>>>> 6c2ba67e6d43c990817f7455d710287af5146462:phase1/src/main/java/edu/toronto/csc207/restaurantsolution/framework/events/EventEmitter.java
    }


}
