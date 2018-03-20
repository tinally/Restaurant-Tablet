package edu.toronto.csc207.restaurantsolution.rmi.client;

import java.rmi.Remote;

public interface UpdateListener extends Remote {
  void update();
}
