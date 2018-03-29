package edu.toronto.csc207.restaurantsolution.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

/**
 * The entry point for the client in the distributed RMI application.
 */
public class MasterGUI extends Application {
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    Parent root = null;
    try {
      if (!NetworkContainer.getSavedNetwork()) {
        URL resource = getClass().getClassLoader().getResource("Network.fxml");
        if (resource != null) {
          root = FXMLLoader.load(resource);
        }
      } else {
        URL resource = getClass().getClassLoader().getResource("Login.fxml");
        if (resource != null) {
          root = FXMLLoader.load(resource);
        }
      }
    } catch (IOException e) {
      // Resources are guaranteed to be in folder if it is found (otherwise, resolution cannot
      // ... occur)
      throw new RuntimeException(e);
    }
    // Resources must be in resources folder for correct loading.
    if (root != null) {
      primaryStage.setTitle("Restaurant Manager");
      primaryStage.setScene(new Scene(root));
      primaryStage.show();
    }
  }
}
