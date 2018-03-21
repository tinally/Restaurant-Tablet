package edu.toronto.csc207.restaurantsolution.remoting.client;

import edu.toronto.csc207.restaurantsolution.remoting.DataServer;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
  private DataServer dataServer;

  public Client(String host) {
    try {
      Registry registry = LocateRegistry.getRegistry(host, DataServer.port);
      dataServer = (DataServer) registry.lookup(DataServer.name);
    } catch (RemoteException | NotBoundException e) {

    }
  }

  public DataServer getDataServer() {
    return dataServer;
  }
}
