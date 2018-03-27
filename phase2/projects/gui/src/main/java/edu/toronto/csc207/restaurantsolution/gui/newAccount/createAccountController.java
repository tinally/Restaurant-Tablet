package edu.toronto.csc207.restaurantsolution.gui.newAccount;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class createAccountController {

    @FXML
    private JFXTextField name;

    @FXML
    private JFXTextField username;

    @FXML
    private JFXCheckBox serverBox;

    @FXML
    private JFXCheckBox receiverBox;

    @FXML
    private JFXCheckBox chefBox;

    @FXML
    private JFXCheckBox cashierBox;

    @FXML
    private JFXCheckBox managerBox;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXPasswordField confirmPassword;

    @FXML
    private JFXButton confirmButton;

    @FXML
    void confirmAction(ActionEvent event) {

    }

}
