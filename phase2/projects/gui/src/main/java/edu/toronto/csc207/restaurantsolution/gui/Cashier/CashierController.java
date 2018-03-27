package edu.toronto.csc207.restaurantsolution.gui.Cashier;

import com.jfoenix.controls.*;
import edu.toronto.csc207.restaurantsolution.model.interfaces.BillRecord;
import edu.toronto.csc207.restaurantsolution.model.interfaces.Order;
import edu.toronto.csc207.restaurantsolution.remoting.DataManager;
import edu.toronto.csc207.restaurantsolution.remoting.DataService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

/**
 * Controls the Cashier graphics user interface.
 */
public class CashierController implements Initializable {

    @FXML
    private JFXComboBox<Integer> tableNumber;

    @FXML
    private GridPane priceGridPane;

    @FXML
    private JFXTextField discount;

    @FXML
    private JFXListView<BillRecord> billableList;

    @FXML
    private JFXToggleButton toggleDiscount;

    @FXML
    private JFXListView<Order> orderList;

    private DataManager manager;

    public CashierController() {
        DataService service = new DataService("localhost");
        manager = service.getDataManager();
        orderList = new JFXListView<>();
        try {
            orderList.getItems().addAll(manager.getAllOrders());
            billableList.getItems().addAll(manager.getAllBills());
        } catch (java.rmi.RemoteException e) {

        }
    }

    public void addToOrderList(Order order) {
        orderList.getItems().add(order);
    }

    @FXML
    void toggleDiscountEvent(ActionEvent event) {
        if (toggleDiscount.isSelected()) {
            if (Pattern.matches("\\d+", discount.getText())) {
                //TODO: import order and update discount
                System.out.println("This is an integer");
            } else {
                System.out.println("This is invalid");
            }
        }
    }

    @FXML
    public void getTable(ActionEvent event) {
        System.out.println("CHECKING");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tableNumber.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8);
    }
}
