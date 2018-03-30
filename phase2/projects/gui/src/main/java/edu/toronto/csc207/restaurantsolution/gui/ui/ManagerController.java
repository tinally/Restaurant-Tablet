package edu.toronto.csc207.restaurantsolution.gui.ui;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import edu.toronto.csc207.restaurantsolution.gui.NetworkContainer;
import edu.toronto.csc207.restaurantsolution.model.interfaces.*;
import edu.toronto.csc207.restaurantsolution.remoting.DataListener;
import edu.toronto.csc207.restaurantsolution.remoting.DataManager;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controls the Manager graphics user interface.
 */
public class ManagerController implements DataListener {
  @FXML
  public TextArea orderDescription;
  @FXML
  public JFXListView<Order> orderList;
  @FXML
  public JFXListView<BillRecord> billList;
  @FXML
  public JFXComboBox<OrderStatus> orderStatusComboBox;
  @FXML
  public TextArea billDescription;
  @FXML
  public TextArea emailText;
  @FXML
  public JFXDatePicker billDatePicker;

  private ObservableList<Order> orderCache = FXCollections.observableArrayList();
  private ObservableList<BillRecord> billCache = FXCollections.observableArrayList();
  private DataManager manager;

  public ManagerController() {
    manager = NetworkContainer.dataManager;
    NetworkContainer.dataService.registerListener(this);
  }

  public void update() {
    try {
      this.orderCache.setAll(manager.getAllOrders());
      this.billCache.setAll(manager.getAllBills());
      updateEmail();
      this.billDatePicker.valueProperty().addListener(e -> {
        this.update();
      });
    } catch (RemoteException e) {
      e.printStackTrace();
    }
  }

  @FXML
  public void initialize() {
    this.orderCache.addListener((ListChangeListener<? super Order>) e -> {

    });
    this.orderStatusComboBox.getSelectionModel().selectedItemProperty().addListener(e -> {
      this.orderList.setItems(FXCollections.observableArrayList(this.orderCache.stream()
          .filter(o -> o.getOrderStatus() == this.orderStatusComboBox.getValue()).collect(Collectors.toList())));
    });
    this.billDatePicker.valueProperty().addListener(e -> {
      this.billList.setItems(FXCollections.observableArrayList(this.billCache.stream()
          .filter(b -> LocalDateTime.ofInstant(b.getBilledDate(),
              ZoneId.systemDefault()).toLocalDate().equals(this.billDatePicker.getValue()))
          .collect(Collectors.toList())));
    });

    this.billList.getSelectionModel().selectedItemProperty().addListener(e -> {
      this.updateBillTextArea();
    });

    this.orderList.getSelectionModel().selectedItemProperty().addListener(e -> {
      this.updateOrderSummary();
    });
    update();
  }

  private void updateEmail() {
    StringBuilder emailBuilder = new StringBuilder();
    emailBuilder.append("Please deliver the following ingredients.")
        .append(System.lineSeparator());
    try {
      List<Ingredient> ingredients = manager.getAllIngredients();
      for (Ingredient i : ingredients) {
        if (manager.getIngredientCount(i) <= i.getReorderThreshold()) {
          emailBuilder.append(i.getName())
              .append(" x")
              .append(i.getDefaultReorderAmount())
              .append(System.lineSeparator());
        }
      }
      this.emailText.setText(emailBuilder.toString());
    } catch (RemoteException e) {
      // Let data server handle exception
      throw new RuntimeException(e);
    }
  }

  private void updateOrderSummary() {
    StringBuilder orderSummary = new StringBuilder();
    if (this.orderList.getSelectionModel().getSelectedItem() == null) return;
    MenuItem selectedItem = this.orderList.getSelectionModel().getSelectedItem().getMenuItem();
    if (selectedItem != null) {
      orderSummary.append(selectedItem.getName()).append(System.lineSeparator());
    }

    for (Ingredient i : this.orderList.getSelectionModel().getSelectedItem().getRemovals()) {
      orderSummary.append(" WITHOUT ").append(i.getName()).append(System.lineSeparator());
    }
    for (Ingredient i : this.orderList.getSelectionModel().getSelectedItem().getAdditions().keySet()) {
      orderSummary.append(" EXTRA ").append(i.getName()).append(System.lineSeparator());
    }

    this.orderDescription.setText(orderSummary.toString());
  }

  private void updateBillTextArea() {
    BillRecord billRecord = this.billList.getSelectionModel().getSelectedItem();
    StringBuilder bill = new StringBuilder();

    bill.append("Restaurant Name")
        .append(System.lineSeparator());
    for (Order o : billRecord.getBilledOrders()) {
      bill.append(o.getMenuItem().getName())
          .append("     $")
          .append(o.getOrderCost())
          .append(System.lineSeparator());
    }
    bill.append("Subtotal: ")
        .append("     ")
        .append(billRecord.getChargedSubtotal())
        .append(System.lineSeparator());
    bill.append("Tip: ")
        .append("     $")
        .append(billRecord.getChargedGratuity())
        .append(System.lineSeparator());
    bill.append("Tax: ")
        .append("     $")
        .append(billRecord.getChargedTax())
        .append(System.lineSeparator());
    bill.append("Discount: ")
        .append("     -$")
        .append(billRecord.getChargedSubtotal() + billRecord.getChargedGratuity()
            + billRecord.getChargedTax() - billRecord.getPaidAmount())
        .append(System.lineSeparator());
    bill.append("Total: ")
        .append("     $")
        .append(billRecord.getPaidAmount())
        .append(System.lineSeparator());
    this.billDescription.setText(bill.toString());
  }
}
