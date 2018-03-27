package edu.toronto.csc207.restaurantsolution.gui.Manager;

import com.jfoenix.controls.JFXButton;
import edu.toronto.csc207.restaurantsolution.gui.NetworkContainer;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import sun.nio.ch.Net;

import java.net.URL;
import java.util.*;

/**
 * Controls the Manager graphics user interface.
 */
public class ManagerController implements Initializable, DataListener {

    private ObservableList<OrderStatus> status = FXCollections.observableArrayList(OrderStatus.CREATED,
            OrderStatus.INPUTTED, OrderStatus.PUSHED, OrderStatus.SEEN, OrderStatus.FILLED, OrderStatus.REJECTED,
            OrderStatus.DELIVERED, OrderStatus.RETURNED);

    @FXML
    private ChoiceBox<OrderStatus> statusBox;

    @FXML
    private VBox ordersBox;

    @FXML
    private VBox orderDesc;

    @FXML
    private Label OrderTitle;

    @FXML
    private VBox orderStatus;

    private DataManager manager;

    public ManagerController() throws Exception {
      NetworkContainer.initManager();
      manager = NetworkContainer.dataManager;
      NetworkContainer.dataService.registerListener(this);
    }

    //TODO: Use Observer design pattern to update orders on both Manager and Chef

    //TODO: Use the same code as previous but add STATUS: enum
    private void showDesc(Order order, JFXButton button) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        statusBox.setValue(OrderStatus.INPUTTED);
        statusBox.getItems().addAll(status);

        statusBox.setOnAction(event -> {
            //TODO: Add a method that calls the Orders that are inputted and the orders that are Delivered
            //TODO: when a choice is selected.
        });
        update();
    }

    public void update(){

    }
}
