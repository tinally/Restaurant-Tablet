package edu.toronto.csc207.restaurantsolution.gui.Receiver;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import edu.toronto.csc207.restaurantsolution.remoting.DataManager;
import edu.toronto.csc207.restaurantsolution.remoting.DataService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * Controls the Receiver graphics user interface.
 */
public class ReceiverController {

    @FXML
    private JFXTextField amount;

    @FXML
    private JFXButton plus;

    @FXML
    private JFXButton minus;

    private DataManager manager;

    public ReceiverController() {
        DataService service = new DataService("localhost");
        manager = service.getDataManager();
    }

    @FXML
    void minusOne(ActionEvent event) {
    }

    @FXML
    void plusOne(ActionEvent event) {
    }

}
