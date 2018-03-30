package edu.toronto.csc207.restaurantsolution.gui.ui;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import edu.toronto.csc207.restaurantsolution.gui.NetworkContainer;
import edu.toronto.csc207.restaurantsolution.model.implementations.OrderImpl;
import edu.toronto.csc207.restaurantsolution.model.interfaces.Ingredient;
import edu.toronto.csc207.restaurantsolution.model.interfaces.MenuItem;
import edu.toronto.csc207.restaurantsolution.model.interfaces.Order;
import edu.toronto.csc207.restaurantsolution.model.interfaces.OrderStatus;
import edu.toronto.csc207.restaurantsolution.remoting.DataListener;
import edu.toronto.csc207.restaurantsolution.remoting.DataManager;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;

import java.rmi.RemoteException;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Controls the Server graphics user interface.
 */
public class ServerController implements DataListener {
  @FXML
  TextArea orderSummaryTextArea;
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
  private final DataManager manager;

  public ServerController() {
    manager = NetworkContainer.dataManager;
    NetworkContainer.dataService.registerListener(this);
  }

  @Override
  public void update() {
    try {
      ObservableList<MenuItem> menuItems = FXCollections.observableArrayList();
      // Only add items with adequate ingredients
      List<MenuItem> availableItems = manager.getAllMenuItems().stream().filter(m -> {
        for (Map.Entry<Ingredient, Integer> requirement : m.getIngredientRequirements().entrySet()) {
          try {
            if (manager.getIngredientCount(requirement.getKey()) < requirement.getValue()) {
              return false;
            }
          } catch (RemoteException e) {
            e.printStackTrace();
            return false;
          }
        }
        return true;
      }).collect(Collectors.toList());
      menuItems.setAll(availableItems);
      menuList.setItems(menuItems);

      List<DeliverableOrderMapping> deliverableOrders = manager.getAllOrders().stream()
          .filter(o -> o.getOrderStatus() == OrderStatus.FILLED)
          .map(o -> new DeliverableOrderMapping(o.getTableNumber(), o.getOrderNumber(), o.getMenuItem(), o))
          .collect(Collectors.toList());

      TreeItem<DeliverableOrderMapping> root =
          new RecursiveTreeItem<>(FXCollections.observableArrayList(deliverableOrders),
              RecursiveTreeObject::getChildren);
      deliverableOrdersTable.setRoot(root);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @FXML
  public void initialize() {
    this.deletionsList.getSelectionModel().setSelectionMode(javafx.scene.control.SelectionMode.MULTIPLE);
    this.deletionsList.getSelectionModel().getSelectedItems().addListener((ListChangeListener<? super Ingredient>) e -> {
      this.updateOrderSummary();
    });
    this.additionsList.getSelectionModel().setSelectionMode(javafx.scene.control.SelectionMode.MULTIPLE);
    this.additionsList.getSelectionModel().getSelectedItems().addListener((ListChangeListener<? super Ingredient>) e -> {
      this.updateOrderSummary();
    });
    this.menuList.getSelectionModel().getSelectedItems().addListener((ListChangeListener<? super MenuItem>) e -> {
      this.deletionsList.getSelectionModel().clearSelection();
      this.additionsList.getSelectionModel().clearSelection();
      this.updateAdditionsAndDeletions(this.menuList.getSelectionModel().getSelectedItem());
      this.updateOrderSummary();
    });
    this.update();
  }

  public void confirmSelectedOrder() throws RemoteException {
    TreeItem<DeliverableOrderMapping> selectedItem = deliverableOrdersTable.getSelectionModel().getSelectedItem();
    if (selectedItem != null) {
      Order order = selectedItem.getValue().getOrder();
      manager.modifyOrder(order, OrderStatus.DELIVERED);
    }
  }

  public void rejectSelectedOrder() throws RemoteException {
    TreeItem<DeliverableOrderMapping> selectedItem = deliverableOrdersTable.getSelectionModel().getSelectedItem();
    if (selectedItem != null) {
      Order order = selectedItem.getValue().getOrder();
      manager.modifyOrder(order, OrderStatus.RETURNED);
    }
  }

  public void sendNewOrder() throws RemoteException {
    Order order = new OrderImpl();

    MenuItem menuItem = menuList.getSelectionModel().getSelectedItem();
    List<Ingredient> additions = additionsList.getSelectionModel().getSelectedItems();

    if (menuItem != null) {
      order.setOrderNumber(new Random().nextInt(10000));
      order.setOrderStatus(OrderStatus.CREATED);
      order.setOrderId(UUID.randomUUID());
      order.setMenuItem(menuItem);
      order.setTableNumber(tableNumberSelection.getValue());
      order.setOrderDate(Instant.now());

      HashMap<Ingredient, Integer> additionsMap = new HashMap<>();
      order.setOrderCost(getOrderCost(menuItem, additions));
      for (Ingredient i : additions) {
        additionsMap.put(i, 1);
      }
      order.setAdditions(additionsMap);
      order.setCreatingUser("system");
      order.setRemovals(new ArrayList<>(deletionsList.getSelectionModel().getSelectedItems()));

      manager.modifyOrder(order);
    }
  }

  /**
   * Gets the order cost with specific ingredient additions.
   *
   * @param m         the menu item.
   * @param additions the extra ingredients.
   * @return the new order cost.
   */
  private Double getOrderCost(MenuItem m, List<Ingredient> additions) {
    double sum = m.getPrice();
    for (Ingredient ingredient : additions)
      sum += ingredient.getPricing();
    return sum;
  }

  private void updateAdditionsAndDeletions(MenuItem menuItem) {
    ObservableList<Ingredient> possibleAdditions = FXCollections.observableArrayList();
    ObservableList<Ingredient> possibleDeletions = FXCollections.observableArrayList();
    if (menuItem != null) {
      try {
        for (Ingredient ingredient : manager.getAllIngredients()) {
          if (manager.getIngredientCount(ingredient) > 0) {
            possibleAdditions.add(ingredient);
          }
        }

        possibleDeletions.addAll(menuItem.getIngredientRequirements().keySet());
      } catch (RemoteException e) {
        // TODO: Handle all exceptions
        e.printStackTrace();
      }
    }
    deletionsList.setItems(possibleDeletions);
    additionsList.setItems(possibleAdditions);
  }

  private void updateOrderSummary() {
    StringBuilder orderSummary = new StringBuilder();

    MenuItem selectedItem = menuList.getSelectionModel().getSelectedItem();
    if (selectedItem != null) {
      orderSummary.append(selectedItem.getName()).append(System.lineSeparator());
    }

    for (Ingredient i : this.deletionsList.getSelectionModel().getSelectedItems()) {
      orderSummary.append(" WITHOUT ").append(i.getName()).append(System.lineSeparator());
    }
    for (Ingredient i : this.additionsList.getSelectionModel().getSelectedItems()) {
      orderSummary.append(" EXTRA ").append(i.getName()).append(System.lineSeparator());
    }

    this.orderSummaryTextArea.setText(orderSummary.toString());
  }
}
