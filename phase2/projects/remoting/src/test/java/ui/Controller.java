package ui;

import edu.toronto.csc207.restaurantsolution.remoting.client.DataClient;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ui.net.ClientListenerImpl;
import ui.net.Unifier;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Controller implements DataListener {
  private Unifier manager;

  @FXML
  public Label label;

  public Controller() throws RemoteException, NotBoundException {
    manager = (Unifier) new DataClient("localhost").getRemoteInterface();
    ClientListenerImpl listener = new ClientListenerImpl(manager);
    listener.addListener(this);
  }

  public void buttonPress() throws RemoteException {
    manager.updateClients();
  }

  public void update() {
    try {
      label.setText(Double.toString(manager.getRandDouble()));
    } catch (RemoteException e) {
      System.out.println(e);
    }
  }

}
