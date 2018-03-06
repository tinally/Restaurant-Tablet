package events;

@FunctionalInterface
public interface RestaurantEventHandler<T extends EventArgs> {
  public void handle(T args);
}
