package chef;

//TODO: Things left to do. Method to display an order once the GUI has been initialized
// TODO: Add a method to remove the order in progress once the order is done
// TODO: Refactor the code and make it look clean


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXProgressBar;
import edu.toronto.csc207.restaurantsolution.model.interfaces.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


import java.net.URL;
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
        Label label = new Label(order.getMenuItem().toString());
        ingredientDesc.getChildren().add(label);
        Map<Ingredient, Integer> ings = order.getMenuItem().getIngredientRequirements();
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
            JFXButton orderButton = new JFXButton(order.toString());
            orderButton.setOnAction(event -> showOrder(order, orderButton));

            incomingOrders.getChildren().addAll(orderButton);
        }
    }
}
