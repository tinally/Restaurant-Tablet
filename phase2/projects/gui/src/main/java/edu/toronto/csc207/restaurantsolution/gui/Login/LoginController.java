package edu.toronto.csc207.restaurantsolution.gui.Login;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Controls the Login graphics user interface.
 */
public class LoginController {

    @FXML
    private JFXTextField username;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXButton login;

    private void setScene(ActionEvent event, URL url) throws IOException {
        System.out.println(url);
        Parent newRoot = FXMLLoader.load(url);
        Scene scene1 = new Scene(newRoot);
        Stage window = (Stage) (((Node) event.getSource()).getScene()).getWindow();
        window.setScene(scene1);
        window.setFullScreen(true);
        window.show();
    }

    @FXML
    void validateUser(ActionEvent event) throws IOException {

        String id = username.getText();
        String pass = password.getText();
        if (id.equals("Chef") && pass.equals("chef")) {
            System.out.println("Logged In");
            URL url = new File("projects/gui/src/main/java/edu/toronto/csc207/restaurantsolution/gui/Chef/Chef.fxml").toURL();
            setScene(event, url);
        } else if (id.equals("Server") && pass.equals("server")) {
            URL url = new File("projects/gui/src/main/java/edu/toronto/csc207/restaurantsolution/gui/Server/Server.fxml").toURL();
            setScene(event, url);
        } else if (id.equals("Receiver") && pass.equals("receiver")) {
            URL url = new File("projects/gui/src/main/java/edu/toronto/csc207/restaurantsolution/gui/Receiver/Receiver.fxml").toURL();
            setScene(event, url);
        } else if (id.equals("Manager") && pass.equals("manager")) {
            URL url = new File("projects/gui/src/main/java/edu/toronto/csc207/restaurantsolution/gui/Manager/Manager.fxml").toURL();
            setScene(event, url);
        } else if (id.equals("Cashier") && pass.equals("cashier")) {
            URL url = new File("projects/gui/src/main/java/edu/toronto/csc207/restaurantsolution/gui/Cashier/Cashier.fxml").toURL();
            setScene(event, url);
        } else {
            System.out.println("Try Again");
        }
    }

}
