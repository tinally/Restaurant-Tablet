package edu.toronto.csc207.restaurantsolution.gui.Chef;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXProgressBar;
import com.sun.org.apache.xpath.internal.operations.Or;
import edu.toronto.csc207.restaurantsolution.gui.NetworkContainer;
import edu.toronto.csc207.restaurantsolution.model.interfaces.Ingredient;
import edu.toronto.csc207.restaurantsolution.model.interfaces.Order;
import edu.toronto.csc207.restaurantsolution.remoting.DataListener;
import edu.toronto.csc207.restaurantsolution.remoting.DataManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.web.HTMLEditor;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Controls the Chef graphics user interface.
 */
public class ChefController implements DataListener {

    private final DataManager manager;

    public ChefController() throws Exception {
        NetworkContainer.initManager();
        manager = NetworkContainer.dataManager;
        NetworkContainer.dataService.registerListener(this);
    }

    @FXML
    JFXListView<Ingredient> itemDisplayIngredientList;

    @FXML
    Label itemDisplayTitle;

    @FXML
    JFXListView<Order> incomingOrderList;

    @FXML
    JFXListView<Order> inProgressOrderList;

    @FXML
    private void initialize() {
        this.update();
        this.incomingOrderList.getSelectionModel().selectedItemProperty().addListener(e -> {
            this.refreshOrderView(this.incomingOrderList.getSelectionModel().getSelectedItem());
        });
    }

    private void refreshOrderView(Order o) {
        this.itemDisplayTitle.setText(o.getMenuItem().getName());
        ObservableList<Ingredient> ingredients = FXCollections.observableArrayList();
        ingredients.addAll(o.getMenuItem().getIngredientRequirements().keySet());
       // ingredients.addAll(o.getAdditions().keySet());
       // ingredients.removeAll(o.getRemovals());
        this.itemDisplayIngredientList.setItems(ingredients);
    }

    @Override
    public void update() {
        ObservableList<Order> orders = null;
        try {
            orders = FXCollections.observableArrayList(this.manager.getAllOrders());
            inProgressOrderList.setItems(orders);
            incomingOrderList.setItems(orders);

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
