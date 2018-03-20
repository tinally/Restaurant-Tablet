package edu.toronto.csc207.restaurantsolution.ui.Sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    @FXML
    private JFXButton toggle;

    @FXML
    private JFXButton addNewNode;

    @FXML
    private JFXListView<JFXButton> listView;

    @FXML
    void addAction(ActionEvent event) {

    }

    @FXML
    void toggleAction(ActionEvent event) {
        if (listView.isExpanded()) {
            listView.setExpanded(true);
            listView.depthProperty().set(1);
        } else {
            listView.setExpanded(false);
            listView.depthProperty().set(0);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (int i = 0; i <4; i++){
            JFXButton lbl = new JFXButton("Item " + i);

            listView.getItems().add(lbl);
        }
    }
}