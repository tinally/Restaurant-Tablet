package edu.toronto.csc207.restaurantsolution.remoting.client;

import edu.toronto.csc207.restaurantsolution.remoting.DataListener;
import edu.toronto.csc207.restaurantsolution.remoting.server.ServerInfo;
import javafx.application.Platform;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

/**
 * A network client of a remote server.
 */
public final class DataClient implements RemoteListener {
  private Remote remoteInterface;
  private ArrayList<DataListener> listeners;

  public DataClient(String host) throws RemoteException, NotBoundException {
    Registry registry = LocateRegistry.getRegistry(host, ServerInfo.port);
    remoteInterface = registry.lookup(ServerInfo.name);
    listeners = new ArrayList<>();
  }

  public Remote getRemoteInterface() {
    return remoteInterface;
  }

  public void registerListener(DataListener listener) {
    listeners.add(listener);
  }

  @Override
  public void update() {
    for (DataListener listener : listeners) {
      Platform.runLater(listener::update);
    }
  }
}
