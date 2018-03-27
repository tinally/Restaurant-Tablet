package edu.toronto.csc207.restaurantsolution.gui.Server;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import edu.toronto.csc207.restaurantsolution.gui.Chef.ChefController;
import edu.toronto.csc207.restaurantsolution.gui.NetworkContainer;
import edu.toronto.csc207.restaurantsolution.model.implementations.OrderImpl;
import edu.toronto.csc207.restaurantsolution.model.interfaces.*;
import edu.toronto.csc207.restaurantsolution.remoting.DataListener;
import edu.toronto.csc207.restaurantsolution.remoting.DataManager;
import edu.toronto.csc207.restaurantsolution.remoting.DataService;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.rmi.RemoteException;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Controls the Server graphics user interface.
 */
public class ServerController implements DataListener {

  public void confirmSelectedOrder(MouseEvent mouseEvent) throws RemoteException {
    manager.modifyOrder(this.deliverableOrdersTable.getSelectionModel().getSelectedItem().getValue().order,
        OrderStatus.DELIVERED);
  }

  public void rejectSelectedOrder(MouseEvent mouseEvent) throws RemoteException {
    manager.modifyOrder(this.deliverableOrdersTable.getSelectionModel().getSelectedItem().getValue().order,
        OrderStatus.RETURNED);
  }

  public static class DeliverableOrderMapping extends RecursiveTreeObject<DeliverableOrderMapping> {
    final IntegerProperty tableNumber;
    final IntegerProperty orderNumber;
    final ObjectProperty<MenuItem> menuItem;
    final Order order;
    DeliverableOrderMapping(Integer tableNumber, Integer orderNumber, MenuItem menuItem, Order order) {
      this.tableNumber = new SimpleIntegerProperty(tableNumber);
      this.orderNumber = new SimpleIntegerProperty(orderNumber);
      this.menuItem = new SimpleObjectProperty<>(menuItem);
      this.order = order;
    }

    public IntegerProperty tableNumberProperty() {
      return tableNumber;
    }

    public IntegerProperty orderNumberProperty() {
      return orderNumber;
    }

    public ObjectProperty<MenuItem> menuItemProperty() {
      return menuItem;
    }
  }
  private final DataManager manager;

  @FXML
  JFXButton sendOrderToKitchenButton;

  @FXML
  TextArea orderSummaryTextArea;

  public ServerController() throws Exception {
    NetworkContainer.initManager();
    manager = NetworkContainer.dataManager;
    NetworkContainer.dataService.registerListener(this);
  }
  @FXML
  JFXButton rejectOrderButton;

  @FXML
  JFXButton confirmOrderButton;

  @FXML
  JFXComboBox<Integer> tableNumberSelection;

  @FXML
  JFXListView<Ingredient> deletionsList;

  @FXML
  JFXListView<MenuItem> menuList;

  @FXML
  JFXListView<Ingredient> additionsList;

  @FXML
  JFXTreeTableView<DeliverableOrderMapping> deliverableOrdersTable;

  @Override
  public void update() {
    try {
      ObservableList<MenuItem> menuItems = FXCollections.observableArrayList(manager.getAllMenuItems());
      menuList.setItems(menuItems);

      ObservableList<DeliverableOrderMapping> orders = FXCollections.observableArrayList();

      List<DeliverableOrderMapping> deliverableOrders = manager.getAllOrders().stream()
          .filter(o -> o.getOrderStatus() == OrderStatus.FILLED)
          .map(o -> new DeliverableOrderMapping(o.getTableNumber(), o.getOrderNumber(), o.getMenuItem(), o))
          .collect(Collectors.toList());

      TreeItem<DeliverableOrderMapping> root =
          new RecursiveTreeItem<>(FXCollections.observableArrayList(deliverableOrders),
          RecursiveTreeObject::getChildren);
      this.deliverableOrdersTable.setRoot(root);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void sendNewOrder(MouseEvent mouseEvent) throws RemoteException {
      OrderImpl order = new OrderImpl();
      order.setOrderNumber(new Random().nextInt(1000));
      order.setOrderStatus(OrderStatus.CREATED);
      order.setOrderId(UUID.randomUUID());
      order.setMenuItem(this.menuList.getSelectionModel().getSelectedItem());
      order.setTableNumber(this.tableNumberSelection.getValue());
      order.setOrderDate(Instant.now());
      order.setOrderCost(this.getOrderCost(this.menuList.getSelectionModel().getSelectedItem(),
          this.additionsList.getSelectionModel().getSelectedItems()));

      HashMap<Ingredient, Integer> additions = new HashMap<>();
      for(Ingredient i : this.additionsList.getSelectionModel().getSelectedItems()) {
        additions.put(i, 1);
      }
      order.setAdditions(additions);
      order.setCreatingUser("system");
      order.setRemovals(new ArrayList<>(this.deletionsList.getSelectionModel().getSelectedItems()));

      manager.modifyOrder(order);

  }

  private Double getOrderCost(MenuItem m, List<Ingredient> additions) {
    return m.getPrice() + additions.stream().map(Ingredient::getPricing).reduce(Double::sum).get();
  }
  private void updateAdditionsAndDeletions(MenuItem item) {
    ObservableList<Ingredient> possibleDeletions = FXCollections
        .observableArrayList(item.getIngredientRequirements().keySet());
    this.deletionsList.setItems(possibleDeletions);
    ObservableList<Ingredient> possibleAdditions = null;
    try {
      possibleAdditions = FXCollections
          .observableArrayList(manager.getAllIngredients());
    } catch (RemoteException e) {
      e.printStackTrace();
    }
    this.additionsList.setItems(possibleAdditions);

  }

  private void updateOrderSummary() {
    StringBuilder orderSummary = new StringBuilder();
    orderSummary.append(this.menuList.getSelectionModel().getSelectedItem().getName())
        .append(System.lineSeparator());

    for (Ingredient i : this.deletionsList.getSelectionModel().getSelectedItems()) {
      orderSummary.append(" WITHOUT ").append(i.getName()).append(System.lineSeparator());
    }
    for (Ingredient i : this.additionsList.getSelectionModel().getSelectedItems()) {
      orderSummary.append(" EXTRA ").append(i.getName()).append(System.lineSeparator());
    }

    this.orderSummaryTextArea.setText(orderSummary.toString());
  }

  @FXML
  public void initialize() {
    this.deletionsList.getSelectionModel().setSelectionMode(javafx.scene.control.SelectionMode.MULTIPLE);
    this.deletionsList.setOnMouseClicked(e -> {
      this.updateOrderSummary();
    });
    this.additionsList.getSelectionModel().setSelectionMode(javafx.scene.control.SelectionMode.MULTIPLE);
    this.additionsList.setOnMouseClicked(e -> {
      this.updateOrderSummary();
    });
    this.menuList.setOnMouseClicked(e -> {
      this.deletionsList.getSelectionModel().clearSelection();
      this.additionsList.getSelectionModel().clearSelection();
      this.updateAdditionsAndDeletions(this.menuList.getSelectionModel().getSelectedItem());
      this.updateOrderSummary();
    });
    this.update();
  }
}
