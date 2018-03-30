package edu.toronto.csc207.restaurantsolution.gui.ui;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import edu.toronto.csc207.restaurantsolution.gui.NetworkContainer;
import edu.toronto.csc207.restaurantsolution.model.implementations.IngredientImpl;
import edu.toronto.csc207.restaurantsolution.model.interfaces.Ingredient;
import edu.toronto.csc207.restaurantsolution.remoting.DataListener;
import edu.toronto.csc207.restaurantsolution.remoting.DataManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.util.converter.IntegerStringConverter;

import java.rmi.RemoteException;

/**
 * Controller for the Receiver UI.
 */
public class ReceiverController implements DataListener {
  public JFXTreeTableColumn<IngredientMapping, Integer> quantityColumn;
  @FXML
  private JFXTextField name;
  @FXML
  private JFXTextField quantity;
  @FXML
  private JFXTextField cost;
  @FXML
  private JFXTextField pricing;
  @FXML
  private JFXTextField reorderAmount;
  @FXML
  private JFXTextField reorderThreshold;
  @FXML
  private JFXTreeTableView<IngredientMapping> inventoryTable;
  private DataManager manager;

  public ReceiverController() {
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
    quantityColumn.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn(new IntegerStringConverter()));
    quantityColumn.setOnEditCommit(ingredientMappingIntegerCellEditEvent -> {
      Integer newValue = ingredientMappingIntegerCellEditEvent.getNewValue();
      Ingredient i = ingredientMappingIntegerCellEditEvent.getRowValue().valueProperty().get().ingredientProperty().get();
      try {
        manager.setIngredientCount(i, newValue);
      } catch (RemoteException e) {
        // Let data server handle exception
        throw new RuntimeException(e);
      }
    });
    update();
  }

  /**
   * Handles inventory data updating and UI refreshing of the inventory count table.
   */
  @FXML
  void updateItem() {
    String ingredientName = name.getText();

    if (!ingredientName.isEmpty()) {
      try {
        Ingredient currentIngredient = manager.getIngredient(ingredientName);
        int currentQuantity = manager.getIngredientCount(currentIngredient);

        int quantityValue;
        double costValue;
        double pricingValue;
        int reorderAmountValue;
        int reorderThresholdValue;

        // If the ingredient has already been registered, use its default values.
        if (currentIngredient == null) {
          quantityValue = 0;
          costValue = 10d;
          pricingValue = 10d;
          reorderAmountValue = 10;
          reorderThresholdValue = 10;
        } else {
          quantityValue = currentQuantity;
          costValue = currentIngredient.getCost();
          pricingValue = currentIngredient.getPricing();
          reorderAmountValue = currentIngredient.getDefaultReorderAmount();
          reorderThresholdValue = currentIngredient.getReorderThreshold();
        }

        if (!quantity.getText().isEmpty()) {
          quantityValue = Integer.parseInt(quantity.getText());
          if (quantityValue < 0)
            throw new NumberFormatException();
        }

        if (!cost.getText().isEmpty()) {
          costValue = Double.parseDouble(cost.getText());
          if (costValue < 0)
            throw new NumberFormatException();
        }

        if (!pricing.getText().isEmpty()) {
          pricingValue = Double.parseDouble(pricing.getText());
          if (pricingValue < 0)
            throw new NumberFormatException();
        }

        if (!reorderAmount.getText().isEmpty()) {
          reorderAmountValue = Integer.parseInt(reorderAmount.getText());
          if (reorderAmountValue < 0)
            throw new NumberFormatException();
        }

        if (!reorderThreshold.getText().isEmpty()) {
          reorderThresholdValue = Integer.parseInt(reorderThreshold.getText());
          if (reorderThresholdValue < 0)
            throw new NumberFormatException();
        }

        Ingredient ingredient = new IngredientImpl();
        ingredient.setName(ingredientName);
        ingredient.setCost(costValue);
        ingredient.setPricing(pricingValue);
        ingredient.setDefaultReorderAmount(reorderAmountValue);
        ingredient.setReorderThreshold(reorderThresholdValue);
        resetTextFields();

        manager.setIngredientCount(ingredient, quantityValue);
        manager.registerIngredient(ingredient);
      } catch (NumberFormatException e) {
        resetTextFields();
      } catch (RemoteException e) {
        // Let data server handle exception
        throw new RuntimeException(e);
      }
    }
  }

  /**
   * Resets all text fields to be blank.
   */
  private void resetTextFields() {
    name.setText("");
    quantity.setText("");
    cost.setText("");
    pricing.setText("");
    reorderAmount.setText("");
    reorderThreshold.setText("");
  }
}
