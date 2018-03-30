package edu.toronto.csc207.restaurantsolution.gui.ui;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import edu.toronto.csc207.restaurantsolution.gui.NetworkContainer;
import edu.toronto.csc207.restaurantsolution.model.interfaces.Ingredient;
import edu.toronto.csc207.restaurantsolution.model.interfaces.Order;
import edu.toronto.csc207.restaurantsolution.model.interfaces.OrderStatus;
import edu.toronto.csc207.restaurantsolution.remoting.DataListener;
import edu.toronto.csc207.restaurantsolution.remoting.DataManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Controls the Chef graphics user interface.
 */
public class ChefController implements DataListener {
  private final DataManager manager;
  @FXML
  JFXTreeTableView<IngredientMapping> itemDisplayIngredientList;
  @FXML
  Label itemDisplayTitle;
  @FXML
  JFXListView<Order> incomingOrderList;
  @FXML
  JFXListView<Order> inProgressOrderList;

  public ChefController() {
    manager = NetworkContainer.dataManager;
    NetworkContainer.dataService.registerListener(this);
  }

  @Override
  public void update() {
    try {
      List<Order> orders = manager.getAllOrders();
      inProgressOrderList.setItems(FXCollections.observableArrayList(orders.stream()
          .filter(o -> o.getOrderStatus() == OrderStatus.SEEN)
          .collect(Collectors.toList())));
      incomingOrderList.setItems(FXCollections.observableArrayList(orders.stream()
          .filter(o -> o.getOrderStatus() == OrderStatus.CREATED || o.getOrderStatus() == OrderStatus.RETURNED)
          .collect(Collectors.toList())));

    } catch (RemoteException e) {
      e.printStackTrace();
    }
  }

  @FXML
  private void initialize() {
    update();

    incomingOrderList.getSelectionModel().selectedItemProperty().addListener(e -> {
      refreshOrderView(incomingOrderList.getSelectionModel().getSelectedItem());
    });

    inProgressOrderList.setOnMouseClicked(m -> {
      if (!m.isPrimaryButtonDown() && m.getClickCount() != 2) return;
      Order order = inProgressOrderList.getSelectionModel().getSelectedItem();
      if (order != null) {
        try {
          for (Map.Entry<Ingredient, Integer> entry : order.getMenuItem().getIngredientRequirements().entrySet()) {
            manager.setIngredientCount(entry.getKey(), manager.getIngredientCount(entry.getKey()) - entry.getValue());
          }
          for (Map.Entry<Ingredient, Integer> entry : order.getAdditions().entrySet()) {
            manager.setIngredientCount(entry.getKey(), manager.getIngredientCount(entry.getKey()) - entry.getValue());
          }
          for (Ingredient ingredient : order.getRemovals()) {
            manager.setIngredientCount(ingredient, manager.getIngredientCount(ingredient) + 1);
          }
          manager.modifyOrder(order, OrderStatus.FILLED);
        } catch (RemoteException e) {
          e.printStackTrace();
        }
      }
    });
  }

  private void refreshOrderView(Order order) {
    ObservableList<IngredientMapping> ingredients = FXCollections.observableArrayList();
    if (order != null) {
      itemDisplayTitle.setText(order.getMenuItem().getName());
      for (Map.Entry<Ingredient, Integer> entry : order.getMenuItem().getIngredientRequirements().entrySet()) {
        if (!order.getRemovals().contains(entry.getKey())) {
          ingredients.add(new IngredientMapping(entry.getKey(), entry.getValue()));
        }
      }
      for (Map.Entry<Ingredient, Integer> entry : order.getAdditions().entrySet()) {
        ingredients.add(new IngredientMapping(entry.getKey(), entry.getValue()));
      }
    }
    TreeItem<IngredientMapping> root = new RecursiveTreeItem<>(ingredients, RecursiveTreeObject::getChildren);
    itemDisplayIngredientList.setRoot(root);
  }

  public void setSelectedOrderSeen() {
    Order order = incomingOrderList.getSelectionModel().getSelectedItem();
    if (order != null) {
      order.setOrderStatus(OrderStatus.SEEN);
      try {
        manager.modifyOrder(order);
      } catch (RemoteException e1) {
        e1.printStackTrace();
      }
    }
  }
}
