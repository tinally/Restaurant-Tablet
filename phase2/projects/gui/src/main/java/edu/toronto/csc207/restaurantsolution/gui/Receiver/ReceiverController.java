package edu.toronto.csc207.restaurantsolution.gui.Receiver;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import edu.toronto.csc207.restaurantsolution.gui.NetworkContainer;
import edu.toronto.csc207.restaurantsolution.model.implementations.IngredientImpl;
import edu.toronto.csc207.restaurantsolution.remoting.DataListener;
import edu.toronto.csc207.restaurantsolution.remoting.DataManager;
import edu.toronto.csc207.restaurantsolution.model.interfaces.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.rmi.RemoteException;

/**
 * Controls the Receiver graphics user interface.
 */
public class ReceiverController implements DataListener {

  @FXML
  private JFXTextField name;
  @FXML
  private JFXTextField quantity;
  @FXML
  private JFXListView<Ingredient> inventoryList;
  // TODO: Hash map

    private DataManager manager;

    public ReceiverController() throws Exception {
      NetworkContainer.initManager();
      manager = NetworkContainer.dataManager;
      NetworkContainer.dataService.registerListener(this);
    }

    @Override
    public void update() {
        try {
            ObservableList<Ingredient> ingredient =
                FXCollections.observableArrayList(manager.getAllIngredients());
            inventoryList.setItems(ingredient);
            System.out.println("Updated!");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        this.update();
        this.inventoryList.getSelectionModel().selectedItemProperty().addListener((e) -> {
            Ingredient selected = this.inventoryList.getSelectionModel().getSelectedItem();
            try {
                Integer selectedcount = manager.getIngredientCount(selected);
            } catch (RemoteException e1) {
                e1.printStackTrace();
            }
        });
    }

    @FXML
    void minusOne(ActionEvent event) {
    }

    @FXML
    void plusOne(ActionEvent event) {
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
        System.out.println("Added Ingredient!");
    }
}
