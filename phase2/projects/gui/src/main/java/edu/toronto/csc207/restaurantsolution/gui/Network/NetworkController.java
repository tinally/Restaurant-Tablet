package edu.toronto.csc207.restaurantsolution.gui.Network;

import com.jfoenix.controls.JFXTextField;
import edu.toronto.csc207.restaurantsolution.gui.NetworkContainer;
import edu.toronto.csc207.restaurantsolution.remoting.DataListener;
import edu.toronto.csc207.restaurantsolution.remoting.DataManager;
import edu.toronto.csc207.restaurantsolution.remoting.DataService;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sun.nio.ch.Net;

import java.io.IOException;

public class NetworkController implements DataListener {
  @FXML
  private JFXTextField ip;

  private void activateLoginView(ActionEvent event) throws IOException {
    FXMLLoader loginLoader = new FXMLLoader(getClass().getClassLoader().getResource("Login.fxml"));
    Parent root = loginLoader.load();
    Scene scene1 = new Scene(root);
    Stage window = (Stage) (((Node) event.getSource()).getScene()).getWindow();
    window.setScene(scene1);
    window.setTitle("Login screen");
    window.show();
  }

  @FXML
  void connect(ActionEvent event) throws IOException {
    boolean success = true;
    String host = ip.getText();
    try {
      NetworkContainer.dataService = new DataService(host);
      NetworkContainer.dataManager = NetworkContainer.dataService.getDataManager();
    } catch (Exception e) {
      success = false;
    }
    ip.setText("");
    if (success)
      activateLoginView(event);
  }

  @Override
  public void update() {

  }
}
