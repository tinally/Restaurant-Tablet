package edu.toronto.csc207.restaurantsolution.gui.Login;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import edu.toronto.csc207.restaurantsolution.gui.MainView.MainViewController;
import edu.toronto.csc207.restaurantsolution.gui.NetworkContainer;
import edu.toronto.csc207.restaurantsolution.model.interfaces.UserAccount;
import edu.toronto.csc207.restaurantsolution.remoting.DataManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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

    private DataManager manager;

    public LoginController() throws Exception {
      NetworkContainer.initManager();
        manager = NetworkContainer.dataManager;
    }

    private void setScene(ActionEvent event, URL url) throws IOException {
        System.out.println(url);
        Parent newRoot = FXMLLoader.load(url);
        Scene scene1 = new Scene(newRoot);
        Stage window = (Stage) (((Node) event.getSource()).getScene()).getWindow();
        window.setScene(scene1);
        window.setFullScreen(true);
        window.show();
    }

    private void activateMainView(UserAccount account, ActionEvent event) throws IOException {
        FXMLLoader mainViewLoader = new FXMLLoader(getClass().getClassLoader().getResource("MainView.fxml"));
        Parent root = (Parent) mainViewLoader.load();
        MainViewController mainViewController = mainViewLoader.getController();
        mainViewController.activateUser(account);
        Scene scene1 = new Scene(root);
        Stage window = (Stage) (((Node) event.getSource()).getScene()).getWindow();
        window.setScene(scene1);
        window.setFullScreen(true);
        window.show();
    }

    @FXML
    void validateUser(ActionEvent event) throws IOException {
        String id = username.getText();
        String pass = password.getText();
        boolean goodLogin = manager.checkLogin(id, pass);
        if (!goodLogin) return;
        UserAccount loggedInUser = manager.getUserAccount(id);
        activateMainView(loggedInUser, event);
    }

}
