package edu.toronto.csc207.restaurantsolution.gui.newAccount;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.Window;

import java.util.regex.Pattern;

public class createAccountController {

  @FXML
  private JFXTextField firstName;

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
  private JFXTextField lastName;

  @FXML
  private JFXCheckBox managerBox;

  @FXML
  private JFXPasswordField password;

  @FXML
  private JFXPasswordField confirmPassword;

  @FXML
  private JFXButton confirmButton;

  private void errorAlert(String message) {
    Window rootWindow = confirmButton.getScene().getWindow();
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Registration Error");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.initOwner(rootWindow);
    alert.show();
  }

  @FXML
  private boolean setEmptyAlerts() {
    if (firstName.getText().trim().isEmpty()) {
      errorAlert("Please enter your first name");
      return true;
    }
    if (lastName.getText().trim().isEmpty()) {
      errorAlert("Please enter your last name");
      return true;
    }
    if (username.getText().trim().isEmpty()) {
      errorAlert("Please enter a username");
      return true;
    }
    if (password.getText().trim().isEmpty()) {
      errorAlert("Please enter a password");
      return true;
    }
    if (!confirmPassword.getText().equals(password.getText())) {
      errorAlert("Your passwords must match");
      return true;
    }
    if (!(chefBox.isSelected() || receiverBox.isSelected() ||
        managerBox.isSelected() || cashierBox.isSelected() || //TODO: clumsy conditional
        serverBox.isSelected())) {
      errorAlert("Please choose at least one permission");
      return true;
    }
    return false;

  }

  private void setRegexAlerts() {
    String regex = "[a-zA-Z]+";
    boolean validFirstName = !Pattern.matches(regex, firstName.getText());
    boolean validLastName = !Pattern.matches(regex, lastName.getText());
    if (validFirstName || validLastName) {
      errorAlert("Please enter a proper name");
    }

  }

  public void confirmAction(ActionEvent event) {
    if (setEmptyAlerts()) {
      return;
    }
    setRegexAlerts();
    //TODO: Do something
  }

}
