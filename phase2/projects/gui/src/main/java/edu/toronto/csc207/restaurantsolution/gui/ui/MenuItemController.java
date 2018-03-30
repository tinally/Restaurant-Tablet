package edu.toronto.csc207.restaurantsolution.gui.ui;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import edu.toronto.csc207.restaurantsolution.gui.NetworkContainer;
import edu.toronto.csc207.restaurantsolution.model.implementations.MenuItemImpl;
import edu.toronto.csc207.restaurantsolution.model.interfaces.Ingredient;
import edu.toronto.csc207.restaurantsolution.remoting.DataListener;
import edu.toronto.csc207.restaurantsolution.remoting.DataManager;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TreeItem;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.util.converter.IntegerStringConverter;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;

public class MenuItemController implements DataListener {
  @FXML
  public JFXListView<Ingredient> inventoryList;
  @FXML
  public JFXTreeTableView<IngredientMapping> inventoryTable;
  @FXML
  public JFXTreeTableColumn<IngredientMapping, Integer> inventoryQuantityColumn;
  @FXML
  public JFXTextField name;
  @FXML
  public JFXTextField cost;
  @FXML
  private final DataManager manager;

  public MenuItemController() {
    manager = NetworkContainer.dataManager;
    NetworkContainer.dataService.registerListener(this);
  }

  @FXML
  public void initialize() {
    ObservableList<IngredientMapping> ingredients = FXCollections.observableArrayList();
    TreeItem<IngredientMapping> root =
        new RecursiveTreeItem<>(ingredients, RecursiveTreeObject::getChildren);
    inventoryTable.setRoot(root);
    inventoryQuantityColumn.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn(new IntegerStringConverter()));
    this.inventoryList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    this.inventoryList.getSelectionModel().getSelectedItems()
        .addListener((ListChangeListener<? super Ingredient>) e -> {
          e.next();
          for (Ingredient removed : e.getRemoved()) {
            inventoryTable.getRoot().getChildren()
                .removeIf(f -> f.valueProperty().get().ingredientProperty().get().equals(removed));
          }
          for (Ingredient added : e.getAddedSubList()) {
            boolean isDupe = inventoryTable.getRoot().getChildren().stream()
                .anyMatch(f -> f.valueProperty().get().ingredientProperty().get().equals(added));
            if (!isDupe) {
              inventoryTable.getRoot().getChildren().add(new TreeItem<>(new IngredientMapping(added, 0)));
            }
          }

        });
    this.update();
  }

  @Override
  public void update() {
    try {
      List<Ingredient> ingredients = this.manager.getAllIngredients();
      this.inventoryList.setItems(FXCollections.observableArrayList(ingredients));
    } catch (RemoteException e) {
      // Let data server handle exception
      throw new RuntimeException(e);
    }
  }

  public void updateItem() {
    MenuItemImpl menuItem = new MenuItemImpl();
    Double price;
    try {
      price = Double.parseDouble(cost.getText());
    } catch (NumberFormatException e) {
      price = 0d;
    }

    menuItem.setPrice(price);
    menuItem.setName(name.getText());
    HashMap<Ingredient, Integer> requirements = new HashMap<Ingredient, Integer>();
    for (TreeItem<IngredientMapping> i : this.inventoryTable.getRoot().getChildren()) {
      requirements.put(i.valueProperty().get().ingredientProperty().get(),
          i.valueProperty().get().quantityProperty().get());
    }
    menuItem.setIngredientRequirements(requirements);
    name.setText("");
    cost.setText("");
    try {
      manager.modifyMenuItem(menuItem);
    } catch (RemoteException e) {
      // Let data server handle exception
      throw new RuntimeException(e);
    }
  }
}
