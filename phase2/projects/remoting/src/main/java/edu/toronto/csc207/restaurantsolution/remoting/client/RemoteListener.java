package edu.toronto.csc207.restaurantsolution.remoting.client;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteListener extends Remote {
  void update() throws RemoteException;
}
