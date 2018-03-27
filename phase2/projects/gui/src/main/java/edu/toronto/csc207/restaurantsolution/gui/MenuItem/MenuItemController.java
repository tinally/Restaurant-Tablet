package edu.toronto.csc207.restaurantsolution.gui.MenuItem;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import edu.toronto.csc207.restaurantsolution.model.interfaces.Ingredient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class MenuItemController implements Initializable{

    @FXML
    private JFXTextField menuItemName;

    private Map<Ingredient, Integer> ingredientMap;

    @FXML
    private JFXTextField menuItemPrice;

    @FXML
    private JFXListView<Ingredient> ingredientList;

    @FXML
    private JFXButton addButton;

    @FXML
    private JFXListView<Ingredient> selectedIngredientList;

    @FXML
    private JFXButton removeButton;

    @FXML
    private JFXTextField newIngredientName;

    @FXML
    private JFXTextField newIngredientCost;

    @FXML
    private JFXTextField newIngredientPrice;

    @FXML
    private JFXTextField newIngredientReorder;

    @FXML
    private JFXTextField newIngredientAmount;

    @FXML
    private JFXButton addNewIngredientButton;

    @FXML
    private JFXButton createMenuItemButton;

    private void addToHashMap(ObservableList<Ingredient> ingredients){
        for (Ingredient item: ingredients){
            if (ingredientMap.containsKey(item)){
                ingredientMap.put(item, ingredientMap.get(item) + 1);
            }
            else{
                ingredientMap.put(item, 1);
            }
        }
    }

    @FXML
    void add(ActionEvent event) {
        ObservableList<Ingredient> ingredients = FXCollections.observableArrayList();
        ingredients.addAll(ingredientList.getSelectionModel().getSelectedItems());
        selectedIngredientList.getItems().addAll(ingredients);
        addToHashMap(ingredients);
    }

    private void removeFromHashMap(ObservableList<Ingredient> ingredients){
        for (Ingredient item: ingredients){
            ingredientMap.remove(item);
        }
    }


    @FXML
    void remove(ActionEvent event) {
        ObservableList<Ingredient> ingredients = selectedIngredientList.getSelectionModel().getSelectedItems();
        removeFromHashMap(ingredients);
        selectedIngredientList.getItems().removeAll(ingredients);
    }

    public void createMenuItem(ActionEvent actionEvent) {
    }

    public void addToIngredient(ActionEvent actionEvent) {
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ingredientList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        selectedIngredientList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        }
}
