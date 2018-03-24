package edu.toronto.csc207.restaurantsolution.gui.Cashier;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class CashierController implements Initializable {

    @FXML
    private JFXComboBox<?> tableNumber;

    @FXML
    private GridPane priceGridPane;

    @FXML
    private JFXTextField discount;

    @FXML
    private JFXToggleButton toggleDiscount;


    // TODO: Use regex instead
    @FXML
    void somethingToggle(ActionEvent event) {
        if(toggleDiscount.isSelected()){
            try{
                System.out.println(Integer.parseInt(discount.getText()));
            }
            catch (NumberFormatException e){
                System.err.println("NOT A VALID NUMBER");
            }
        }
    }

    @FXML
    public void getTable(ActionEvent event) {
        System.out.println("CHECKING");

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
