package edu.toronto.csc207.restaurantsolution.remoting;

import edu.toronto.csc207.restaurantsolution.remoting.client.RemoteListener;
import edu.toronto.csc207.restaurantsolution.remoting.server.UpdateServer;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * A remote data manager to be used by clients in updating and distributing
 * data.
 */
public interface DataManager extends Remote, UpdateServer {

}
