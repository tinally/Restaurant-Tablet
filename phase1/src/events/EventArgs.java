package events;

public abstract class EventArgs<T extends EventArgs> {

  private Class<T> eventClass;

  public EventArgs(Class<T> eventClass) {

    this.eventClass = eventClass;
  }

  private boolean isCancelled = false;

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
