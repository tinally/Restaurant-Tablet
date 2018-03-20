package edu.toronto.csc207.restaurantsolution.rmi.client;

import java.rmi.Remote;

public interface DataListener extends Remote {
  public void handleData(DataCollection data);
}
