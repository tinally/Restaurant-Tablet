package events;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class EventArgs<T extends EventArgs> {

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
