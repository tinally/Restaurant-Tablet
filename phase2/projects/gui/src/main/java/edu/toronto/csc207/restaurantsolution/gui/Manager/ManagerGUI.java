package edu.toronto.csc207.restaurantsolution.gui.Manager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class ManagerGUI extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource(
        "Manager.fxml")));
    primaryStage.setTitle("Manager");
    primaryStage.setScene(new Scene(root, 800, 600));
    primaryStage.setFullScreen(true);
    primaryStage.show();
  }
}
