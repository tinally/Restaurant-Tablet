package events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventEmitter {

  private Map<Class<? extends EventArgs>, List<RestaurantEventHandler<? extends EventArgs>>> eventHandlers;

  public EventEmitter() {
    this.eventHandlers = new HashMap<>();
  }

  public <T extends EventArgs> void registerEventHandler(RestaurantEventHandler<T> eventHandler,
                                                         Class<T> event) {
    this.eventHandlers.putIfAbsent(event, new ArrayList<>());
    this.eventHandlers.get(event).add(eventHandler);
  }

  @SuppressWarnings("unchecked")
  public <T extends EventArgs> void onEvent(T eventArgs, Object sender) {
    for (RestaurantEventHandler<?> eventHandler : this.eventHandlers.get(eventArgs.getEventClass())) {
      if (!eventArgs.isCancelled()) {
        ((RestaurantEventHandler<T>) eventHandler).handle(eventArgs, sender);
      }
    }
  }


}
