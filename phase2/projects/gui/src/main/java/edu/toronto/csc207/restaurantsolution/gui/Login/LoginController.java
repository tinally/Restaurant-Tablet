package edu.toronto.csc207.restaurantsolution.gui.Login;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import edu.toronto.csc207.restaurantsolution.gui.MainView.MainViewController;
import edu.toronto.csc207.restaurantsolution.model.interfaces.UserAccount;
import edu.toronto.csc207.restaurantsolution.remoting.DataManager;
import edu.toronto.csc207.restaurantsolution.remoting.DataService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

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

    public LoginController() {
        DataService service = new DataService("localhost");
        manager = service.getDataManager();
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
