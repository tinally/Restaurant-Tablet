package edu.toronto.csc207.restaurantsolution.gui.Cashier;

import com.jfoenix.controls.*;
import edu.toronto.csc207.restaurantsolution.gui.NetworkContainer;
import edu.toronto.csc207.restaurantsolution.model.interfaces.BillRecord;
import edu.toronto.csc207.restaurantsolution.model.interfaces.Order;
import edu.toronto.csc207.restaurantsolution.model.interfaces.OrderStatus;
import edu.toronto.csc207.restaurantsolution.remoting.DataListener;
import edu.toronto.csc207.restaurantsolution.remoting.DataManager;
import edu.toronto.csc207.restaurantsolution.remoting.DataService;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Controls the Cashier graphics user interface.
 */
public class CashierController implements DataListener {

    public TextArea billTextValue;
    public JFXTextField discountField;
    public JFXTextField tipField;
    public JFXListView billableList;
    public JFXListView<Order> orderList;
    public JFXComboBox<Integer> tableNumber;
    private DataManager manager;

    private ObservableList<Order> orderCache;
    public CashierController() throws Exception {
      NetworkContainer.initManager();
      manager = NetworkContainer.dataManager;
      this.orderCache = FXCollections.observableArrayList();
      this.orderCache.addListener((ListChangeListener<? super Order>) e -> {
            this.updateTableOrders();
      });
      NetworkContainer.dataService.registerListener(this);
    }

    public void initialize() {
        this.update();
    }

    private void updateTableOrders() {
        Integer tableNumber = this.tableNumber.getValue();
        this.orderList.setItems(FXCollections
            .observableArrayList(this.orderCache.stream().filter(o -> o.getTableNumber().equals(tableNumber)
                && o.getOrderStatus() == OrderStatus.DELIVERED).collect(Collectors.toList())));
    }

    @Override
    public void update() {
        try {
            this.orderCache.setAll(this.manager.getAllOrders());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void tableNumberChanged(ActionEvent actionEvent) {
        this.billableList.getItems().clear();
        this.updateTableOrders();
    }
}
