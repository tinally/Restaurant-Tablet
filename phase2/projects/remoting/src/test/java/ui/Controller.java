package ui;

import edu.toronto.csc207.restaurantsolution.remoting.client.Client;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ui.net.ClientListener;
import ui.net.Unifier;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Controller extends UnicastRemoteObject implements ClientListener {
  private Unifier manager;

  @FXML
  public Label label;

  public Controller() throws RemoteException, NotBoundException {
    manager = (Unifier) new Client("localhost").getRemoteInterface();
    manager.registerListener(this);
  }

  public void buttonPress() throws RemoteException {
    manager.updateClients();
  }

  public void update() {
    Platform.runLater(() -> {
      try {
        label.setText(Double.toString(manager.getRandDouble()));
      } catch (Exception e) {
        System.out.println("Error! " + e);
      }});
  }

}
