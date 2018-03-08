package edu.toronto.csc207.restaurantsolution.framework.events;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Represents the arguments of an Event.
 * @param <T> The type of the event.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class EventArgs<T extends EventArgs> {

  /**
   * Required to serialize empty events.
   */
  private boolean emptyEvent;

  /**
   * The class of the event,
   */
  @JsonIgnore private transient Class<T> eventClass;

  public EventArgs(Class<T> eventClass) {
    this.eventClass = eventClass;
  }

  @JsonIgnore private transient boolean isCancelled = false;

  public final boolean isCancelled() {
    return isCancelled;
  }

  public final void setCancelled(boolean cancelled) {
    isCancelled = cancelled;
  }

  public Class<T> getEventClass() {
    return eventClass;
  }
}
