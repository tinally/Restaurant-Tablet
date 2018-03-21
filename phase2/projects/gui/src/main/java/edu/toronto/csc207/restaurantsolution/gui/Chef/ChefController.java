package edu.toronto.csc207.restaurantsolution.gui.Chef;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXProgressBar;
import edu.toronto.csc207.restaurantsolution.model.interfaces.Ingredient;
import edu.toronto.csc207.restaurantsolution.model.interfaces.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class ChefController implements Initializable {

    private static ObservableList<Order> orders = FXCollections.observableArrayList(); //TODO: maybe change it from static?

    @FXML
    private VBox inProgress;

    @FXML
    private VBox incomingOrders;

    @FXML
    private VBox ingredientDesc;



    public static void addOrders(List<Order> orderList){
        orders.addAll(orderList);
    }

    // TODO: Remove all code smells
    private void showOrder(Order order, JFXButton button) {

        ingredientDesc.getChildren().retainAll();
        Label label = new Label(order.getMenuItem().getName());
        ingredientDesc.getChildren().add(label);
        Map<Ingredient, Integer> ings = getUpdatedIngredients(order);
        for (Map.Entry<Ingredient, Integer> entry : ings.entrySet()) {
            Ingredient key = entry.getKey();
            Integer value = entry.getValue();
            Label labeling = new Label(value.toString() + "x " + key.toString());
            ingredientDesc.getChildren().add(labeling);
        }
        JFXButton authorize = new JFXButton("I Authorize");
        ingredientDesc.getChildren().add(authorize);
        authorize.setOnAction(event -> moveToOther(order, button));
    }

    private Map<Ingredient, Integer> getUpdatedIngredients(Order order){
        Map<Ingredient, Integer> menuIng = order.getMenuItem().getIngredientRequirements();
        List<Ingredient> removedIngredients = order.getRemovals();
        Map<Ingredient, Integer> additions = order.getAdditions();
        Map<Ingredient, Integer> updatedIngs = new HashMap<>();

        for (Map.Entry<Ingredient, Integer> ingredients: menuIng.entrySet()){
            if (!removedIngredients.contains(ingredients.getKey())){ // If the ingredient is not in removed ingredients
                if (additions.containsKey(ingredients)){ // if the ingredient is in the added ing
                    updatedIngs.put(ingredients.getKey(), additions.get(ingredients.getKey()));
                }
                else{
                    updatedIngs.put(ingredients.getKey(), ingredients.getValue());
                }
            }
        }

        return updatedIngs;
    }
    //TODO: refactor code to make it look clean
    private void moveToOther(Order order, JFXButton button){
        HBox hBox = new HBox();
        Label orderLabel = new Label("Order #" + order.getOrderNumber());
        JFXProgressBar progress = new JFXProgressBar();
        progress.setMaxWidth(39);
        hBox.getChildren().addAll(orderLabel, progress);
        inProgress.getChildren().addAll(hBox);

        ingredientDesc.getChildren().retainAll();
        incomingOrders.getChildren().remove(button);
        orders.remove(order);

    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (Order order: orders){
            JFXButton orderButton = new JFXButton("Order # + " + order.getOrderID());
            orderButton.setOnAction(event -> showOrder(order, orderButton));

            incomingOrders.getChildren().addAll(orderButton);
        }
    }
}
