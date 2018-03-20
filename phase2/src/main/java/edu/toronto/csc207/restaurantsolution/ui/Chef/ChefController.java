package edu.toronto.csc207.restaurantsolution.ui.Chef;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class ChefController {

    @FXML
    private JFXButton orderItem1;
    private boolean clickeditem1 = false;

    @FXML
    private JFXButton orderItem2;
    private boolean clickeditem2;

    @FXML
    private JFXButton orderItem3;
    private boolean clicked3;

    @FXML
    private Pane descriptionPane;

    @FXML
    private VBox vboxDesc;
    private JFXButton done = new JFXButton("Completed Preparing");


    @FXML
    void orderDesc1(ActionEvent event) {
        if(!clickeditem1) {
            vboxDesc.getChildren().retainAll();
            Label title = new Label("Egg Sandwich");
            Label ing1 = new Label("1x Egg");
            Label ing2 = new Label("2x Bread");


            vboxDesc.getChildren().addAll(title, ing1, ing2, done);
            clickeditem2 = false;
            clicked3 = false;
        }
        completeOrder();
        clickeditem1 = true;
    }

    @FXML
    void orderDesc2(ActionEvent event) {
        if(!clickeditem2) {
            vboxDesc.getChildren().retainAll();
            Label title = new Label("Tomato Sandwich");
            Label ing1 = new Label("1x Tomato");
            Label ing2 = new Label("2x Bread");


            vboxDesc.getChildren().addAll(title, ing1, ing2, done);
            clickeditem1 = false; clicked3 = false;

        }
        completeOrder();
        clickeditem2 = true;

    }

    @FXML
    void orderDesc3(ActionEvent event) {
        if(!clicked3) {
            vboxDesc.getChildren().retainAll();
            Label title = new Label("Mac and Cheese Sandwich");
            Label ing1 = new Label("1x Macaroni");
            Label ing2 = new Label("2x Cheese");
            Label ing3 = new Label("2x Bread");

            vboxDesc.getChildren().addAll(title, ing1, ing2, ing3, done);
            clickeditem1 = false; clickeditem2 = false;
        }
        completeOrder();
        clicked3 = true;
    }

    private void completeOrder(){
        done.setOnAction(e ->  vboxDesc.getChildren().retainAll());
    }

}
