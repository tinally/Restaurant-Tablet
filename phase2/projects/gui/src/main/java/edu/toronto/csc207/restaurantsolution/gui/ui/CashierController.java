package edu.toronto.csc207.restaurantsolution.gui.ui;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import edu.toronto.csc207.restaurantsolution.gui.NetworkContainer;
import edu.toronto.csc207.restaurantsolution.model.implementations.BillRecordImpl;
import edu.toronto.csc207.restaurantsolution.model.interfaces.BillRecord;
import edu.toronto.csc207.restaurantsolution.model.interfaces.Order;
import edu.toronto.csc207.restaurantsolution.model.interfaces.OrderStatus;
import edu.toronto.csc207.restaurantsolution.remoting.DataListener;
import edu.toronto.csc207.restaurantsolution.remoting.DataManager;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.NumberStringConverter;

import java.rmi.RemoteException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.UUID;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Controls the Cashier graphics user interface.
 */
public class CashierController implements DataListener {
  @FXML
  public TextArea billTextValue;
  @FXML
  public JFXTextField discountField;
  @FXML
  public JFXTextField tipField;
  @FXML
  public JFXListView<Order> billableList;
  @FXML
  public JFXListView<Order> orderList;
  @FXML
  public JFXComboBox<Integer> tableNumber;
  @FXML
  private ObservableList<Order> orderCache;
  private DataManager manager;

  public CashierController() {
    manager = NetworkContainer.dataManager;
    this.orderCache = FXCollections.observableArrayList();
    this.orderCache.addListener((ListChangeListener<? super Order>) e -> this.updateTableOrders());
    NetworkContainer.dataService.registerListener(this);
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
  public void initialize() {
    this.orderList.getSelectionModel().setSelectionMode(javafx.scene.control.SelectionMode.MULTIPLE);
    this.tipField.setTextFormatter(new TextFormatter<>(new NumberStringConverter()));
    this.discountField.setTextFormatter(new TextFormatter<>(new NumberStringConverter()));
    this.orderList.getSelectionModel().getSelectedItems().addListener((ListChangeListener<? super Order>) e -> this.updateBillableList());
    this.update();
  }

  /**
   * Updates orders shown based on selected table number.
   */
  @FXML
  private void updateTableOrders() {
    Integer tableNumber = this.tableNumber.getValue();
    this.orderList.setItems(FXCollections
        .observableArrayList(orderCache.stream().filter(o -> o.getTableNumber().equals(tableNumber)
            && o.getOrderStatus() == OrderStatus.DELIVERED).collect(Collectors.toList())));
  }

  /**
   * Updates orders shown based on selected table number.
   */
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
    String doubleRegex = "\\d+(\\.\\d+)?";
    StringBuilder bill = new StringBuilder();
    Double subTotal = this.billableList.getItems().stream().map(Order::getOrderCost).reduce(Double::sum)
        .orElse(0d);
    Double tipAmount = 0d;
    if (Pattern.matches(intRegex, this.tipField.getText())) {
      tipAmount = (Integer.parseInt(this.tipField.getText()) * 0.01) * subTotal;
    }
    Double discountAmount = 0d;
    if (Pattern.matches(doubleRegex, this.discountField.getText())) {
      discountAmount = Double.parseDouble(this.discountField.getText());
    }

    Double taxAmount = .13 * (subTotal + tipAmount);
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

  public void sendBill() throws RemoteException {
    String intRegex = "\\d+";
    String doubleRegex = "\\d+(\\.\\d+)?";
    BillRecord bill = new BillRecordImpl();
    bill.setBilledOrders(new ArrayList<>(this.billableList.getItems()));
    Double subTotal = this.billableList.getItems().stream().map(Order::getOrderCost).reduce(Double::sum)
        .orElse(0d);
    Double tipAmount = 0d;
    if (Pattern.matches(intRegex, this.tipField.getText())) {
      tipAmount = (Integer.parseInt(this.tipField.getText()) * 0.01) * subTotal;
    }

    bill.setChargedGratuity(tipAmount);
    bill.setBilledDate(Instant.now());
    bill.setChargedSubtotal(subTotal);
    bill.setBillID(UUID.randomUUID());
    Double taxAmount = .13 * (subTotal + tipAmount);
    Double discountAmount = 0d;
    if (Pattern.matches(doubleRegex, this.discountField.getText())) {
      discountAmount = Double.parseDouble(this.discountField.getText());
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
