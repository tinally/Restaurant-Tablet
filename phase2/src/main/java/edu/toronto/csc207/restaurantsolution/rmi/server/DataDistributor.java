package edu.toronto.csc207.restaurantsolution.rmi.server;

import edu.toronto.csc207.restaurantsolution.rmi.client.DataCollection;
import edu.toronto.csc207.restaurantsolution.rmi.client.DataListener;

/**
 * Pushes data to a cluster of remote clients.
 */
public interface DataDistributor {
  /**
   * Registers a remote client to which data will be pushed.
   *
   * @param client the remote client that will handle pushed data.
   */
  public void registerClient(DataListener client);

  /**
   * Pushes data to all registered remote clients.
   */
  public void pushData(DataCollection data);
}
