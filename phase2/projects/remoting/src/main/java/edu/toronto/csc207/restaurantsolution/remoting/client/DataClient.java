package edu.toronto.csc207.restaurantsolution.remoting.client;

import edu.toronto.csc207.restaurantsolution.remoting.DataListener;
import edu.toronto.csc207.restaurantsolution.remoting.server.ServerInfo;
import edu.toronto.csc207.restaurantsolution.remoting.server.UpdateServer;
import javafx.application.Platform;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.logging.*;

/**
 * A client of the distributed RMI application that translates the remote server interface into a
 * local object and transforms remote data updates into local data updates for system threading
 * coordination.
 *
 * This class cannot be subclassed and should be instantiated only once per JVM; otherwise,
 * networking conflicts may occur.
 */
public final class DataClient extends UnicastRemoteObject implements RemoteListener {
  /** The interface that links client with server, allowing for remote method invocation. */
  private UpdateServer remoteInterface;
  /** Local data listeners that will respond to remote data updates. */
  private ArrayList<DataListener> listeners;

  /**
   * Constructs a new data client.
   *
   * @param registry the RMI registry to be used for lookup of the remote server interface.
   * @throws RemoteException if a remote error occurs.
   * @throws NotBoundException if the remote server interface is not bound to the registry.
   */
  public DataClient(Registry registry) throws RemoteException, NotBoundException {
    // Retrieves remote interface, registers this client as a listener of the server's data updates.
    remoteInterface = (UpdateServer) registry.lookup(ServerInfo.name);
    remoteInterface.registerListener(this);
    listeners = new ArrayList<>();
  }

  /** Gets the remote interface that allows this client to invoke methods on the server directly. */
  public UpdateServer getRemoteInterface() {
    return remoteInterface;
  }

  /**
   * Registers a local data listener that will handle remote data updates in a local scope.
   *
   * @param listener the data listener that will handle updates.
   */
  public void registerListener(DataListener listener) {
    listeners.add(listener);
  }

  @Override
  public void update() {
    // Schedules update method to run alongside all running Threads.
    // This is done to avoid Thread conflicts.
    for (DataListener listener : listeners) {
      Platform.runLater(listener::update);
    }
  }
}
