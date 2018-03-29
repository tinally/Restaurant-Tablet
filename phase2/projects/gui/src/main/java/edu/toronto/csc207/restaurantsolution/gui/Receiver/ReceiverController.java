package edu.toronto.csc207.restaurantsolution.gui.Receiver;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import edu.toronto.csc207.restaurantsolution.gui.NetworkContainer;
import edu.toronto.csc207.restaurantsolution.model.implementations.IngredientImpl;
import edu.toronto.csc207.restaurantsolution.model.interfaces.Ingredient;
import edu.toronto.csc207.restaurantsolution.remoting.DataListener;
import edu.toronto.csc207.restaurantsolution.remoting.DataManager;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * Controls the Receiver graphics user interface.
 */
public class ReceiverController implements DataListener {

  public static class IngredientMapping extends RecursiveTreeObject<IngredientMapping> {
    final ObjectProperty<Ingredient> ingredient;
    final IntegerProperty quantity;

    IngredientMapping(Ingredient ingredient, Integer quantity) {
      this.ingredient = new SimpleObjectProperty<>(ingredient);
      this.quantity = new SimpleIntegerProperty(quantity);
    }

    public ObjectProperty<Ingredient> ingredientProperty() {
      return ingredient;
    }

    public IntegerProperty quantityProperty() {
      return quantity;
    }
  }

  @FXML
  private JFXTextField name;
  @FXML
  private JFXTextField quantity;
  @FXML
  private JFXTreeTableView<IngredientMapping> inventoryTable;

  private DataManager manager;

  public ReceiverController() throws Exception {
    NetworkContainer.initManager();
    manager = NetworkContainer.dataManager;
    NetworkContainer.dataService.registerListener(this);
  }

  @Override
  public void update() {
    try {
      ObservableList<IngredientMapping> ingredients = FXCollections.observableArrayList();

      for (Ingredient ingredient : manager.getAllIngredients())
        ingredients.add(new IngredientMapping(ingredient, manager.getIngredientCount(ingredient)));

      TreeItem<IngredientMapping> root =
          new RecursiveTreeItem<>(ingredients, RecursiveTreeObject::getChildren);
      inventoryTable.setRoot(root);
    } catch (RemoteException e) {
      e.printStackTrace();
    }
  }

  @FXML
  public void initialize() {
    this.update();
  }

  @FXML
  void addItem(ActionEvent actionEvent) {
    String ingredientName = name.getText();
    name.setText("");
    Integer amount = Integer.parseInt(quantity.getText());
    quantity.setText("");
    IngredientImpl ingredient = new IngredientImpl();
    ingredient.setCost(10d);
    ingredient.setPricing(10d);
    ingredient.setName(ingredientName);
    ingredient.setDefaultReorderAmount(10);
    ingredient.setReorderThreshold(10);
    try {
      manager.setIngredientCount(ingredient, amount);
    } catch (RemoteException e) {
      e.printStackTrace();
    }
  }
}
