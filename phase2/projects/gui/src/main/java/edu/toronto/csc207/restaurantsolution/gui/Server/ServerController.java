package edu.toronto.csc207.restaurantsolution.gui.Server;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import edu.toronto.csc207.restaurantsolution.gui.NetworkContainer;
import edu.toronto.csc207.restaurantsolution.model.implementations.OrderImpl;
import edu.toronto.csc207.restaurantsolution.model.interfaces.MenuItem;
import edu.toronto.csc207.restaurantsolution.model.interfaces.Order;
import edu.toronto.csc207.restaurantsolution.model.interfaces.OrderStatus;
import edu.toronto.csc207.restaurantsolution.remoting.DataListener;
import edu.toronto.csc207.restaurantsolution.remoting.DataManager;
import edu.toronto.csc207.restaurantsolution.remoting.DataService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.rmi.RemoteException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.UUID;

/**
 * Controls the Server graphics user interface.
 */
public class ServerController implements Initializable, DataListener {
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

    private ObservableList<MenuItem> menuItem;

    @FXML
    private JFXButton sendToKitchenButton;

    public ServerController() throws Exception {
        NetworkContainer.initManager();
        manager = NetworkContainer.dataManager;
        NetworkContainer.dataService.registerListener(this);
    }

    @FXML
    void sendToKitchen(ActionEvent event) throws RemoteException {
        MenuItem menuItem = this.menuItemList.getSelectionModel().getSelectedItem();
        OrderImpl order = new OrderImpl();
        order.setOrderId(UUID.randomUUID());
        order.setOrderStatus(OrderStatus.CREATED);
        order.setAdditions(new HashMap<>());
        order.setRemovals(new ArrayList<>());
        order.setOrderCost(menuItem.getPrice());
        order.setTableNumber(this.tableNumberBox.getValue());
        order.setCreatingUser("Test User");
        order.setOrderDate(Instant.now());
        order.setOrderNumber(137); //todo: make this fancier
        order.setMenuItem(menuItem);
        manager.modifyOrder(order);
        System.out.println("Added order!"); //todo: make popup
    }

    public void update() {
        try {
            menuItem = FXCollections.observableArrayList(manager.getAllMenuItems());
            menuItemList.setItems(menuItem);
            System.out.println("Updated!");
        } catch(Exception e) {

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableNumberBox.getItems().addAll(1, 2, 3, 4, 5, 6, 7);
        this.update();
    }
}
