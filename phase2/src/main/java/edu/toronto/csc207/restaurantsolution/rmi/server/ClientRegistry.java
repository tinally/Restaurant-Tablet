package edu.toronto.csc207.restaurantsolution.rmi.server;

import edu.toronto.csc207.restaurantsolution.rmi.client.DataCollection;
import edu.toronto.csc207.restaurantsolution.rmi.client.DataListener;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class ClientRegistry implements DataDistributor {
  private ArrayList<DataListener> clients;

  public ClientRegistry() throws RemoteException {
    clients = new ArrayList<>();
  }

  @Override
  public void registerClient(DataListener client) {
    clients.add(client);
  }

  @Override
  public void pushData(DataCollection data) {
    for (DataListener client : clients)
      client.handleData(data);
  }
}
