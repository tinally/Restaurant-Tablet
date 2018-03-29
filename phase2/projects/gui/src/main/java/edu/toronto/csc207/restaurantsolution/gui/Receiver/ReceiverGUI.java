package edu.toronto.csc207.restaurantsolution.gui.Receiver;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class ReceiverGUI extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource(
        "Receiver.fxml")));
    primaryStage.setTitle("ReceiverGUI");
    primaryStage.setScene(new Scene(root));
    primaryStage.setFullScreen(true);
    primaryStage.show();
  }
}
