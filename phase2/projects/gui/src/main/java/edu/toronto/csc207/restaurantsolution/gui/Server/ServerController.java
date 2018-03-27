package edu.toronto.csc207.restaurantsolution.gui.Server;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import edu.toronto.csc207.restaurantsolution.model.interfaces.MenuItem;
import edu.toronto.csc207.restaurantsolution.model.interfaces.Order;
import edu.toronto.csc207.restaurantsolution.remoting.DataManager;
import edu.toronto.csc207.restaurantsolution.remoting.DataService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;

/**
 * Controls the Server graphics user interface.
 */
public class ServerController implements Initializable {
    private DataManager manager;

    @FXML
    private JFXListView<Order> deliverOrderList;

    @FXML
    private JFXComboBox<Integer> tableNumberBox;

    @FXML
    private VBox orderDescription;

    @FXML
    private JFXListView<MenuItem> menuItemList;

    @FXML
    private JFXListView<MenuItem> additionsList;

    @FXML
    private JFXListView<MenuItem> deletionsList;

    @FXML
    private JFXButton sendToKitchenButton;

    public ServerController() {
        DataService service = new DataService("localhost");
        manager = service.getDataManager();
        menuItemList = new JFXListView<>();
        additionsList = new JFXListView<>();
        deletionsList = new JFXListView<>();
        try {
            menuItemList.getItems().addAll(manager.getAllMenuItems());
        } catch(java.rmi.RemoteException e) {

        }
    }

    @FXML
    void sendToKitchen(ActionEvent event) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableNumberBox.getItems().addAll(1, 2, 3, 4, 5, 6, 7);
    }
}
