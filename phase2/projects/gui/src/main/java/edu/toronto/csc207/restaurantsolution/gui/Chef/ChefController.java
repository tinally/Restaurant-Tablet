package edu.toronto.csc207.restaurantsolution.gui.Chef;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import edu.toronto.csc207.restaurantsolution.gui.NetworkContainer;
import edu.toronto.csc207.restaurantsolution.model.implementations.OrderImpl;
import edu.toronto.csc207.restaurantsolution.model.interfaces.Ingredient;
import edu.toronto.csc207.restaurantsolution.model.interfaces.Order;
import edu.toronto.csc207.restaurantsolution.model.interfaces.OrderStatus;
import edu.toronto.csc207.restaurantsolution.remoting.DataListener;
import edu.toronto.csc207.restaurantsolution.remoting.DataManager;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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

  public static class IngredientCountMapping extends RecursiveTreeObject<IngredientCountMapping> {
    final StringProperty ingredientName;
    final IntegerProperty ingredientCount;

    IngredientCountMapping(Ingredient ingredient, Integer integerCount) {
      this.ingredientName = new SimpleStringProperty(ingredient.getName());
      this.ingredientCount = new SimpleIntegerProperty(integerCount);
    }

    public StringProperty ingredientNameProperty() {
      return ingredientName;
    }

    public IntegerProperty ingredientCountProperty() {
      return ingredientCount;
    }
  }

  private final DataManager manager;

  public ChefController() throws Exception {
    NetworkContainer.initManager();
    manager = NetworkContainer.dataManager;
    NetworkContainer.dataService.registerListener(this);
  }

  @FXML
  JFXTreeTableView<IngredientCountMapping> itemDisplayIngredientList;

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

    this.inProgressOrderList.setOnMouseClicked(e -> {
      if (!e.isPrimaryButtonDown() && e.getClickCount() != 2) return;
      OrderImpl order = (OrderImpl) this.inProgressOrderList.getSelectionModel().getSelectedItem();
      if (order != null) {
        order.setOrderStatus(OrderStatus.FILLED);
        try {
          manager.modifyOrder(order);
        } catch (RemoteException e1) {
          e1.printStackTrace();
        }
      }
    });
  }

  private void refreshOrderView(Order o) {
    if (o != null) {
      this.itemDisplayTitle.setText(o.getMenuItem().getName());
      ObservableList<IngredientCountMapping> ingredients = FXCollections.observableArrayList();
      for (Map.Entry<Ingredient, Integer> entry : o.getMenuItem().getIngredientRequirements().entrySet()) {
        ingredients.add(new IngredientCountMapping(entry.getKey(), entry.getValue()));
      }
      for (Map.Entry<Ingredient, Integer> entry : o.getAdditions().entrySet()) {
        ingredients.add(new IngredientCountMapping(entry.getKey(), entry.getValue()));
      }
      TreeItem<IngredientCountMapping> root = new RecursiveTreeItem<>(ingredients, RecursiveTreeObject::getChildren);
      this.itemDisplayIngredientList.setRoot(root);
    }
  }

  public void setSelectedOrderSeen() {
    Order order = incomingOrderList.getSelectionModel().getSelectedItem();
    // TODO: OrderImpl/Order intf!!
    if (order != null) {
      ((OrderImpl) order).setOrderStatus(OrderStatus.SEEN);
      try {
        manager.modifyOrder(order);
      } catch (RemoteException e1) {
        e1.printStackTrace();
      }
    }
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
}
