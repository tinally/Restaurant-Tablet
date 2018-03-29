package edu.toronto.csc207.restaurantsolution.gui.newAccount;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class createAccountGUI extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource(
        "CreateAccount.fxml")));
    primaryStage.setTitle("Create Account");
    Scene scene = new Scene(root, 800, 600);
    primaryStage.setScene(scene);
    primaryStage.setFullScreen(true);
    primaryStage.show();
  }
}
