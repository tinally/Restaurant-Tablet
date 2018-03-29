package edu.toronto.csc207.restaurantsolution.gui.Manager;

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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import sun.nio.ch.Net;

import java.net.URL;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Controls the Manager graphics user interface.
 */
public class ManagerController implements DataListener {

    public TextArea orderDescription;
    public JFXListView<Order> orderList;
    public JFXListView<BillRecord> billList;
    public JFXComboBox<OrderStatus> orderStatusCombobox;
    public TextArea billDescription;
    public TextArea emailText;
    public JFXDatePicker billDatePicker;

    private ObservableList<Order> orderCache = FXCollections.observableArrayList();
    private ObservableList<BillRecord> billCache = FXCollections.observableArrayList();
    private DataManager manager;

    public ManagerController() throws Exception {
      NetworkContainer.initManager();
      manager = NetworkContainer.dataManager;
      NetworkContainer.dataService.registerListener(this);
    }

    @FXML
    public void initialize() {
        this.orderCache.addListener((ListChangeListener<? super Order>) e -> {

        });
        this.orderStatusCombobox.getSelectionModel().selectedItemProperty().addListener(e -> {
            this.orderList.setItems(FXCollections.observableArrayList(this.orderCache.stream()
                    .filter(o -> o.getOrderStatus() == this.orderStatusCombobox.getValue()).collect(Collectors.toList())));
        });
        this.billDatePicker.valueProperty().addListener(e -> {
            this.billList.setItems(FXCollections.observableArrayList(this.billCache.stream()
                    .filter(b -> LocalDateTime.ofInstant(b.getBilledDate(),
                            ZoneId.systemDefault()).toLocalDate().equals(this.billDatePicker.getValue()))
                    .collect(Collectors.toList())));
        });
        update();
    }

    public void update(){
        try {
            this.orderCache.setAll(manager.getAllOrders());
            this.billCache.setAll(manager.getAllBills());

            this.billDatePicker.valueProperty().addListener(e -> {
                this.update();
            });
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
