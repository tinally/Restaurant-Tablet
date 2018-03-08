package edu.toronto.csc207.restaurantsolution.framework.events;

@FunctionalInterface
public interface RestaurantEventHandler<T extends EventArgs> {
  public void handle(T args);
}
