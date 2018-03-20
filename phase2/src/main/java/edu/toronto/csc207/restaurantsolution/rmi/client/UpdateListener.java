package edu.toronto.csc207.restaurantsolution.rmi.client;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface UpdateListener extends Remote {
  void update() throws RemoteException;
}
