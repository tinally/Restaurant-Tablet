package edu.toronto.csc207.restaurantsolution.ui.Manager;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.*;

public class ManagerController implements Initializable {

    @FXML
    private JFXButton button1;
    private boolean clicked1 = false;

    @FXML
    private JFXButton button2;
    private boolean clicked2 = false;

    @FXML
    private JFXButton button3;
    private boolean clicked3 = false;

    @FXML
    private VBox orderStatus;


    @FXML
    void button1clicked(ActionEvent event) {
        if(!clicked1) {
            orderStatus.getChildren().retainAll();
            Label title = new Label("Bacon Sandwich");
            Label ing1 = new Label("1x Bacon");
            Label ing2 = new Label("2x Bread");
            Label status = new Label("Status: PAID");


            orderStatus.getChildren().addAll(title, ing1, ing2, status);
            clicked2 = false;
            clicked3 = false;
        }
        clicked1 = true;
    }

    @FXML
    void button2clicked(ActionEvent event) {
        if(!clicked2) {
            orderStatus.getChildren().retainAll();
            Label title = new Label("Mac and Cheese");
            Label ing1 = new Label("1x Pasta");
            Label ing2 = new Label("2x Cheese");
            Label status = new Label("Status: FILLED");


            orderStatus.getChildren().addAll(title, ing1, ing2, status);
            clicked1 = false;
            clicked3 = false;
        }
        clicked2 = true;
    }

    @FXML
    void button3clicked(ActionEvent event) {

        if(!clicked3) {
            orderStatus.getChildren().retainAll();
            Label title = new Label("Chicken Sandwich");
            Label ing1 = new Label("1x Chicked");
            Label ing2 = new Label("2x Bread");
            Label status = new Label("Status: DELIVERED");


            orderStatus.getChildren().addAll(title, ing1, ing2, status);
            clicked1 = false;
            clicked2 = false;
        }
        clicked3 = true;
    }

    private ObservableList<String> status = FXCollections.observableArrayList("Pending", "Delivered");

    @FXML
    private ChoiceBox choice;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        choice.setValue("Pending");
        choice.getItems().addAll(status);
    }
}
