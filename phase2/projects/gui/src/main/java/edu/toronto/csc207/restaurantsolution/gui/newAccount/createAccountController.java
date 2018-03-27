package edu.toronto.csc207.restaurantsolution.gui.newAccount;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import edu.toronto.csc207.restaurantsolution.remoting.DataManager;
import edu.toronto.csc207.restaurantsolution.remoting.DataService;
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

    private DataManager manager;

    public createAccountController() {
        DataService service = new DataService("localhost");
        manager = service.getDataManager();
    }

    @FXML
    void confirmAction(ActionEvent event) {

    }

}
