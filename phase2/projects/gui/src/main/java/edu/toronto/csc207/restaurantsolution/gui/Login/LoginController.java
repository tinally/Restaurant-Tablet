package edu.toronto.csc207.restaurantsolution.gui.Login;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import edu.toronto.csc207.restaurantsolution.gui.Chef.ChefGUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class LoginController {

    @FXML
    private JFXTextField username;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXButton login;

    @FXML
    void validateUser(ActionEvent event) {

        if (username.getText().equals("CSC207") && password.getText().equals("phase2")){
            System.out.println("Logged In");
            }
        else{
            System.out.println("Try Again");
        }
    }

}
