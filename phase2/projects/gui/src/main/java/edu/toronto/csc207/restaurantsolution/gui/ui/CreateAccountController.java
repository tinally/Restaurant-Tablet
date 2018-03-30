package edu.toronto.csc207.restaurantsolution.gui.ui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import edu.toronto.csc207.restaurantsolution.gui.NetworkContainer;
import edu.toronto.csc207.restaurantsolution.remoting.DataManager;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.Window;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Controller for creating new accounts.
 */
public class CreateAccountController {
  private final DataManager manager;
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

  public CreateAccountController() {
    this.manager = NetworkContainer.dataManager;
  }

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
        managerBox.isSelected() || cashierBox.isSelected() ||
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

  public void confirmAction() {
    if (setEmptyAlerts()) {
      return;
    }
    setRegexAlerts();
    ArrayList<String> permissions = new ArrayList<>();
    if (managerBox.isSelected()) {
      permissions.add("view.manager");
    }
    if (serverBox.isSelected()) {
      permissions.add("view.server");
    }
    if (cashierBox.isSelected()) {
      permissions.add("view.cashier");
    }
    if (chefBox.isSelected()) {
      permissions.add("view.chef");
    }
    if (receiverBox.isSelected()) {
      permissions.add("view.receiver");
    }

    String displayName = this.firstName.getText() + " " + this.lastName.getText();
    String accountName = this.username.getText();
    String password = this.password.getText();

    try {
      manager.createAccount(accountName, password, displayName, permissions);
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      Window rootWindow = confirmButton.getScene().getWindow();
      alert.setTitle("Account Created");
      alert.setHeaderText("Account Created");
      alert.setContentText("New account " + accountName + " registered.");
      alert.initOwner(rootWindow);
      alert.show();
    } catch (RemoteException e) {
      // Let data server handle exception
      throw new RuntimeException(e);
    }
  }

}
