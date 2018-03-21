package edu.toronto.csc207.restaurantsolution.remoting.server;

import edu.toronto.csc207.restaurantsolution.remoting.API;
import edu.toronto.csc207.restaurantsolution.remoting.client.UpdateListener;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class MasterAPI implements API {
  private ArrayList<UpdateListener> clients;

  public MasterAPI() throws RemoteException {
    clients = new ArrayList<>();
  }

  @Override
  public void registerClient(UpdateListener client) {
    clients.add(client);
  }
}
