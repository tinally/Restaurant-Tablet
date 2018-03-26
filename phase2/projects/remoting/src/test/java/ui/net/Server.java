package ui.net;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class Server implements Unifier {
  private ArrayList<ClientListener> listeners;

  Server() {
    listeners = new ArrayList<>();
  }

  @Override
  public void registerListener(ClientListener listener) {
    listeners.add(listener);
  }

  public double getRandDouble() {
    return Math.random();
  }

  public void updateClients() throws RemoteException {
    for (ClientListener listener : listeners)
      listener.update();
  }
}
