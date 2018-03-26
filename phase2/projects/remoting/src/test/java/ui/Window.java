package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Window extends Application {
  @Override
  public void start(Stage primaryStage) throws IOException {
    Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Test.fxml"));
    primaryStage.setMinHeight(450);
    primaryStage.setMinWidth(600);
    primaryStage.setTitle("UI Test");
    primaryStage.setScene(new Scene(root, 800, 600));

    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
