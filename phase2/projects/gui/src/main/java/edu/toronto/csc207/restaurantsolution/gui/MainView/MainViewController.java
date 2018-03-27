package edu.toronto.csc207.restaurantsolution.gui.MainView;

import com.jfoenix.controls.JFXTabPane;
import edu.toronto.csc207.restaurantsolution.gui.NetworkContainer;
import edu.toronto.csc207.restaurantsolution.model.interfaces.UserAccount;
import edu.toronto.csc207.restaurantsolution.remoting.DataManager;
import edu.toronto.csc207.restaurantsolution.remoting.DataService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import sun.nio.ch.Net;

import java.util.*;

public class MainViewController {

  @FXML
  private JFXTabPane mainPane;

  @FXML
  private Tab loginTab, cashierTab, chefTab, managerTab, receiverTab, serverTab;

  @FXML
  private Map<String, Tab> menuTabs = new HashMap<>();

  private DataManager manager;

  public MainViewController() {
    NetworkContainer.initManager();
    manager = NetworkContainer.dataManager;
  }

  @FXML
  private void initialize() {
    System.out.println("Hello World");

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
          System.out.println(s);
          if(menuTabs.containsKey(s)) {
            mainPane.getTabs().add(menuTabs.get(s));
          }
        });
  }
}
