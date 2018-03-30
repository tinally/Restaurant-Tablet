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

  private final DataManager manager;
  public JFXListView<Ingredient> inventoryList;
  public JFXTreeTableView<IngredientMapping> inventoryTable;
  public JFXTreeTableColumn<IngredientMapping, Integer> inventoryQuantityColumn;
  public JFXTextField name;
  public JFXTextField cost;

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
      e.printStackTrace();
    }
  }

  public void updateItem(ActionEvent actionEvent) {
    MenuItemImpl menuItem = new MenuItemImpl();
    Double price = 0d;
    try {
      price = Double.parseDouble(this.cost.getText());
    } catch (NumberFormatException e) {

    }

    menuItem.setPrice(price);
    menuItem.setName(this.name.getText());
    HashMap<Ingredient, Integer> requirements = new HashMap<Ingredient, Integer>();
    for (TreeItem<IngredientMapping> i : this.inventoryTable.getRoot().getChildren()) {
      requirements.put(i.valueProperty().get().ingredientProperty().get(),
          i.valueProperty().get().quantityProperty().get());
    }
    menuItem.setIngredientRequirements(requirements);
    try {
      manager.modifyMenuItem(menuItem);
    } catch (RemoteException e) {
      e.printStackTrace();
    }
  }
}
