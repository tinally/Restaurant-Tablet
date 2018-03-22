package edu.toronto.csc207.restaurantsolution.gui.Chef;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXProgressBar;
import edu.toronto.csc207.restaurantsolution.model.interfaces.Ingredient;
import edu.toronto.csc207.restaurantsolution.model.interfaces.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
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


    /**
     * Given a list of Orders, add the orders to the incoming orders to be displayed
     * @param orderList The list of Orders to be displayed.
     */
    public static void addOrders(List<Order> orderList){
        orders.addAll(orderList);
    }

    // TODO: Remove all code smells

    /**
     * Show the Order on the Ingredient Description Pane once the button was clicked
     * @param order The order associated with the button
     * @param button The button that causes the event to occur
     */
    private void showOrder(Order order, JFXButton button) {

        ingredientDesc.getChildren().retainAll();
        Label label = new Label(order.getMenuItem().getName());
        ingredientDesc.getChildren().add(label);
        Map<Ingredient, Integer> recipe = getUpdatedIngredients(order);
        for (Map.Entry<Ingredient, Integer> entry : recipe.entrySet()) {
            Ingredient key = entry.getKey();
            Integer value = entry.getValue();
            Label labeling = new Label(value.toString() + "x " + key.toString());
            ingredientDesc.getChildren().add(labeling);
        }
        JFXButton authorize = new JFXButton("I Authorize");
        ingredientDesc.getChildren().add(authorize);
        authorize.setOnAction(event -> moveToInProgress(order, button));
    }

    /**
     * Given an Order, return a Map of the updated Ingredients to be used for the order
     * @param order The Order
     * @return The Map of updated ingredients to be used
     */
    private Map<Ingredient, Integer> getUpdatedIngredients(Order order){
        Map<Ingredient, Integer> menuIng = order.getMenuItem().getIngredientRequirements();
        List<Ingredient> removedIngredients = order.getRemovals();
        Map<Ingredient, Integer> additions = order.getAdditions();
        Map<Ingredient, Integer> updatedIngs = new HashMap<>();

        // TODO: NullPointerException due to getRemovals and getAdditions being null when initiated.
        for (Map.Entry<Ingredient, Integer> ingredients: menuIng.entrySet()){
            Ingredient ingredient = ingredients.getKey();
            Integer amount = ingredients.getValue();
            if (!removedIngredients.contains(ingredient)){ // If the ingredient is not in removed ingredients
                // if the ingredient is in the added ing
                updatedIngs.put(ingredient, additions.getOrDefault(ingredient, amount));
            }
        }

        return updatedIngs;
    }
    //TODO: refactor code to make it look clean

    /**
     * Move the order to the inProgress pane once the order has been authorized
     * @param order The order to be moved to the Pane
     * @param button The button that causes this event to occur
     */
    private void moveToInProgress(Order order, JFXButton button){
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


    /**
     * Given an order, add the Order to the incoming orders Pane
     * @param order the Order to be added.
     */
    public void addOrderToIncDisp(Order order){
        JFXButton orderButton = new JFXButton("Order # " + order.getOrderNumber());
        orderButton.setOnAction(event -> showOrder(order, orderButton));

        incomingOrders.getChildren().addAll(orderButton);
    }


    /**
     * Overrides Initializable interface. Initialize the GUI and add the orders
     * in the list of incoming orders.
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (Order order: orders){
            addOrderToIncDisp(order);
        }
    }
}
