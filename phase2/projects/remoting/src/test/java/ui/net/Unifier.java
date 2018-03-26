package ui.net;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Unifier extends Remote {
  void registerListener(ClientListener listener) throws RemoteException;
  double getRandDouble() throws RemoteException;
  void updateClients() throws RemoteException;
}
