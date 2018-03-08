package edu.toronto.csc207.restaurantsolution.framework.events;

/**
 * Represents a method that takes in an {@link EventArgs} and
 * does something with the event.
 *
 * @param <T> The type of the Event
 */
@FunctionalInterface
public interface RestaurantEventHandler<T extends EventArgs> {
  void handle(T args);
}
