package ui.net;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientListener extends Remote {
  void update() throws RemoteException;
}
