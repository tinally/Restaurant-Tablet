package edu.toronto.csc207.restaurantsolution.remoting.server;

import edu.toronto.csc207.restaurantsolution.remoting.DataServer;
import edu.toronto.csc207.restaurantsolution.remoting.client.UpdateListener;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class Server implements DataServer {
  private ArrayList<UpdateListener> listeners;

  public Server() {
    listeners = new ArrayList<>();
  }

  @Override
  public void registerClient(UpdateListener listener) {
    listeners.add(listener);
  }

  public void updateListeners() {
    try {
      for (UpdateListener listener : listeners)
        listener.update();
    } catch (RemoteException e) {

    }
  }
}
