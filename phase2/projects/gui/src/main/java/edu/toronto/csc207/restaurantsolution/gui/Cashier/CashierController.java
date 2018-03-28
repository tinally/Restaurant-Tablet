package edu.toronto.csc207.restaurantsolution.gui.Cashier;

import com.jfoenix.controls.*;
import com.sun.org.apache.xpath.internal.operations.Or;
import edu.toronto.csc207.restaurantsolution.gui.NetworkContainer;
import edu.toronto.csc207.restaurantsolution.model.implementations.BillRecordImpl;
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
import javafx.scene.control.TextFormatter;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.util.converter.NumberStringConverter;

import java.net.URL;
import java.rmi.RemoteException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Controls the Cashier graphics user interface.
 */
public class CashierController implements DataListener {

    public TextArea billTextValue;
    public JFXTextField discountField;
    public JFXTextField tipField;
    public JFXListView<Order> billableList;
    public JFXListView<Order> orderList;
    public JFXComboBox<Integer> tableNumber;
    private DataManager manager;

    private ObservableList<Order> orderCache;
    public CashierController() throws Exception {
      NetworkContainer.initManager();
      manager = NetworkContainer.dataManager;
      this.orderCache = FXCollections.observableArrayList();
      this.orderCache.addListener((ListChangeListener<? super Order>) e -> this.updateTableOrders());
      NetworkContainer.dataService.registerListener(this);
    }

    public void initialize() {
        this.orderList.getSelectionModel().setSelectionMode(javafx.scene.control.SelectionMode.MULTIPLE);
        this.tipField.setTextFormatter(new TextFormatter<>(new NumberStringConverter()));
        this.discountField.setTextFormatter(new TextFormatter<>(new NumberStringConverter()));
        this.orderList.getSelectionModel().getSelectedItems().addListener((ListChangeListener<? super Order>) e -> this.updateBillableList());
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
    void tableNumberChanged() {
        this.billableList.setItems(FXCollections.emptyObservableList());
        this.updateTableOrders();
    }

    @FXML
    void updateBillableList() {
        this.billableList.setItems(this.orderList.getSelectionModel().getSelectedItems());
        this.billTextValue.setText(this.getBillString());
        this.billTextValue.setEditable(false);
    }

    private String getBillString() {
        String intRegex = "\\d+";
        String doubleRegex= "\\d+(\\.\\d+)?";
        StringBuilder bill = new StringBuilder();
        Double subTotal = this.billableList.getItems().stream().map(Order::getOrderCost).reduce(Double::sum)
            .orElse(0d);
        Double tipAmount = 0d;
        if (Pattern.matches(intRegex, this.tipField.getText())){
            tipAmount = (Integer.parseInt(this.tipField.getText()) * 0.01) * subTotal;
        }
        Double discountAmount = 0d;
        if (Pattern.matches(doubleRegex, this.discountField.getText())){
            discountAmount =  Double.parseDouble(this.discountField.getText());
        }

        Double taxAmount =.13 * (subTotal + tipAmount);
        bill.append("Restaurant Name")
            .append(System.lineSeparator());
        for (Order o : this.billableList.getItems()) {
            bill.append(o.getMenuItem().getName())
                .append("     $")
                .append(o.getOrderCost())
            .append(System.lineSeparator());
        }
        bill.append("Subtotal: ")
            .append("     ")
            .append(subTotal)
        .append(System.lineSeparator());
        bill.append("Tip: ")
            .append("     $")
            .append(tipAmount)
            .append(System.lineSeparator());
        bill.append("Tax: ")
            .append("     $")
            .append(taxAmount)
            .append(System.lineSeparator());
        bill.append("Discount: ")
            .append("     -$")
            .append(discountAmount)
            .append(System.lineSeparator());
        bill.append("Total: ")
            .append("     $")
            .append(subTotal + tipAmount + taxAmount - discountAmount)
            .append(System.lineSeparator());
        return bill.toString();
    }

    public void sendBill(ActionEvent actionEvent) throws RemoteException {
        String intRegex = "\\d+";
        String doubleRegex= "\\d+(\\.\\d+)?";
        BillRecordImpl bill = new BillRecordImpl();
        bill.setBilledOrders(new ArrayList<>(this.billableList.getItems()));
        Double subTotal = this.billableList.getItems().stream().map(Order::getOrderCost).reduce(Double::sum)
            .orElse(0d);
        Double tipAmount = 0d;
        if (Pattern.matches(intRegex, this.tipField.getText())){
            tipAmount = (Integer.parseInt(this.tipField.getText()) * 0.01) * subTotal;
        }

        bill.setChargedGratuity(tipAmount);
        bill.setBilledDate(Instant.now());
        bill.setChargedSubtotal(subTotal);
        bill.setBillID(UUID.randomUUID());
        Double taxAmount =.13 * (subTotal + tipAmount);
        Double discountAmount = 0d;
        if (Pattern.matches(doubleRegex, this.discountField.getText())){
            discountAmount =  Double.parseDouble(this.discountField.getText());
        }

        bill.setChargedTax(taxAmount);
        bill.setPaidAmount(subTotal + tipAmount + taxAmount - discountAmount);
        manager.modifyBillRecord(bill);
        for (Order o : bill.getBilledOrders()) {
            manager.modifyOrder(o, OrderStatus.PAID);
        }
        this.tableNumberChanged();
    }
}
