package edu.toronto.csc207.restaurantsolution.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MasterGUI extends Application {
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root;
    if (!NetworkContainer.getSavedNetwork()) {
      root = FXMLLoader.load(getClass().getClassLoader().getResource("Network.fxml"));
    } else {
      root = FXMLLoader.load(getClass().getClassLoader().getResource("Login.fxml"));
    }
    primaryStage.setTitle("Restaurant Manager");
    primaryStage.setScene(new Scene(root));
    primaryStage.show();
  }
}
