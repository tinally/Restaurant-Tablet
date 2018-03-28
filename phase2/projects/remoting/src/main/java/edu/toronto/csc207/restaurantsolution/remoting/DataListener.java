package edu.toronto.csc207.restaurantsolution.remoting;

/**
 * An interface for a local data listener whose update invocations will be scheduled in order to
 * cooperate with invocations across threads.
 */
public interface DataListener {
  /**
   * Handles a data update. This method should only be called by a local subject that has
   * registered this local listener.
   */
  void update();
}
