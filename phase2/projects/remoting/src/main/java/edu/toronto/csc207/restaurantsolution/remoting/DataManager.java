package edu.toronto.csc207.restaurantsolution.remoting;

import edu.toronto.csc207.restaurantsolution.remoting.server.UpdateServer;

import java.rmi.RemoteException;

/**
 * A remote data manager to be used by synchronized clients of the distributed RMI application in
 * updating and distributing data.
 */
public interface DataManager extends UpdateServer {
  /*
   * As a sub-interface of Remote, every method specified in this interface must be able to throw a
   * RemoteException in the case of remote errors.
   */
}
