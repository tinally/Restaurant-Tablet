package edu.toronto.csc207.restaurantsolution.gui.ui;

import com.jfoenix.controls.JFXTabPane;
import edu.toronto.csc207.restaurantsolution.model.interfaces.UserAccount;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainViewController {
  @FXML
  private JFXTabPane mainPane;
  @FXML
  private Tab loginTab, cashierTab, chefTab, managerTab, receiverTab, serverTab;
  @FXML
  private Map<String, Tab> menuTabs = new HashMap<>();

  @FXML
  private void initialize() {
    menuTabs.put("login", loginTab);
    menuTabs.put("cashier", cashierTab);
    menuTabs.put("chef", chefTab);
    menuTabs.put("manager", managerTab);
    menuTabs.put("receiver", receiverTab);
    menuTabs.put("server", serverTab);
    mainPane.getTabs().clear();
  }

  public void activateUser(UserAccount userAccount) {
    mainPane.getTabs().clear();
    List<String> permissions = userAccount.getPermissions();
    permissions.stream().distinct().filter(s -> s.startsWith("view."))
        .map(s -> s.substring(5))
        .forEach(s -> {
          if (menuTabs.containsKey(s)) {
            mainPane.getTabs().add(menuTabs.get(s));
          }
        });
  }
}
