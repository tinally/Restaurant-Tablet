package edu.toronto.csc207.restaurantsolution.gui.Chef;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import edu.toronto.csc207.restaurantsolution.gui.NetworkContainer;
import edu.toronto.csc207.restaurantsolution.model.interfaces.Ingredient;
import edu.toronto.csc207.restaurantsolution.model.interfaces.Order;
import edu.toronto.csc207.restaurantsolution.remoting.DataListener;
import edu.toronto.csc207.restaurantsolution.remoting.DataManager;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;

import java.rmi.RemoteException;
import java.util.Map;

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
    }

    private void refreshOrderView(Order o) {
        this.itemDisplayTitle.setText(o.getMenuItem().getName());
        ObservableList<IngredientCountMapping> ingredients = FXCollections.observableArrayList();
        for(Map.Entry<Ingredient, Integer> entry : o.getMenuItem().getIngredientRequirements().entrySet()) {
          ingredients.add(new IngredientCountMapping(entry.getKey(), entry.getValue()));
        }
        TreeItem<IngredientCountMapping> root = new RecursiveTreeItem<>(ingredients, RecursiveTreeObject::getChildren);
        this.itemDisplayIngredientList.setRoot(root);
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
