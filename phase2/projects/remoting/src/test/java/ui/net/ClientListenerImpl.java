package ui.net;

import javafx.application.Platform;
import ui.DataListener;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class ClientListenerImpl extends UnicastRemoteObject implements ClientListener {
  private ArrayList<DataListener> listeners;

  public ClientListenerImpl(Unifier manager) throws RemoteException {
    manager.registerListener(this);
    listeners = new ArrayList<>();
  }

  public void addListener(DataListener listener) {
    listeners.add(listener);
  }

  @Override
  public void update() {
    for (DataListener listener : listeners)
      Platform.runLater(listener::update);
  }
}
