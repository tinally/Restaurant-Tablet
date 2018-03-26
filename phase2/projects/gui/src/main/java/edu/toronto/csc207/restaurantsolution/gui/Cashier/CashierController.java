package edu.toronto.csc207.restaurantsolution.gui.Cashier;

import com.jfoenix.controls.*;
import edu.toronto.csc207.restaurantsolution.model.interfaces.Order;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class CashierController implements Initializable {

    @FXML
    private JFXComboBox<Integer> tableNumber;

    @FXML
    private GridPane priceGridPane;

    @FXML
    private JFXTextField discount;

    @FXML
    private JFXListView<?> billableList; //TODO: What is the type of billableList?

    @FXML
    private JFXToggleButton toggleDiscount;

    @FXML
    private JFXListView<Order> orderList;

    public void addToOrderList(Order order){
        orderList.getItems().add(order);
    }

    // TODO: Use regex instead
    @FXML
    void toggleDiscountEvent(ActionEvent event) {
        if(toggleDiscount.isSelected()){
            System.out.println("ok");
            if (Pattern.matches("\\d+", discount.getText())){
                System.out.println("This is an integer");
            }
            else{
                System.out.println("Invalid");
            }
        }
    }

    @FXML
    public void getTable(ActionEvent event) {
        System.out.println("CHECKING");

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tableNumber.getItems().addAll(1,2,3,4,5,6,7,8);
    }
}
