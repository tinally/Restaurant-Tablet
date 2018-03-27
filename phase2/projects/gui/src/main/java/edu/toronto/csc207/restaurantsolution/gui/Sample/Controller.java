package edu.toronto.csc207.restaurantsolution.gui.Sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import edu.toronto.csc207.restaurantsolution.gui.NetworkContainer;
import edu.toronto.csc207.restaurantsolution.remoting.DataManager;
import edu.toronto.csc207.restaurantsolution.remoting.DataService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controls the Main graphics user interface.
 */
public class Controller implements Initializable {

    @FXML
    private JFXButton toggle;

    @FXML
    private JFXButton addNewNode;

    @FXML
    private JFXListView<JFXButton> listView;

    private DataManager manager;

    public Controller() {
      NetworkContainer.initManager();
        manager = NetworkContainer.dataManager;
    }

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
        for (int i = 0; i < 4; i++) {
            JFXButton lbl = new JFXButton("Item " + i);

            listView.getItems().add(lbl);
        }
    }
}