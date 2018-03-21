package chef;

import edu.toronto.csc207.restaurantsolution.gui.Chef.ChefController;
import edu.toronto.csc207.restaurantsolution.model.implementations.IngredientImpl;
import edu.toronto.csc207.restaurantsolution.model.implementations.MenuItemImpl;
import edu.toronto.csc207.restaurantsolution.model.implementations.OrderImpl;
import edu.toronto.csc207.restaurantsolution.model.interfaces.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class TestChefGUI extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        URL home = new File("projects/gui/src/main/java/edu/toronto/csc207/restaurantsolution/gui/Chef/Chef.fxml").toURL();
        Parent root = FXMLLoader.load(home);
        primaryStage.setTitle("GUI.Chef Screen");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        IngredientImpl ing1 = new IngredientImpl("Burger", 25.5, 55.6,20);
        IngredientImpl ing2 = new IngredientImpl("Tomato", 3.2,5.6,20);
        IngredientImpl ing3 = new IngredientImpl("BLT", 3.2,5.6,20);
        IngredientImpl ing4 = new IngredientImpl("JellyFish", 3.2,5.6,20);

        HashMap<Ingredient, Integer> ingredients = new HashMap<>();
        ingredients.put(ing1, 3);
        ingredients.put(ing2, 4);
        ingredients.put(ing3,5);
        ingredients.put(ing4, 2);

        MenuItemImpl menuItem1 = new MenuItemImpl("Burger Sandwich", ingredients,25.5 );
        MenuItemImpl menuItem2 = new MenuItemImpl("Second Burger", ingredients, 24.5);

        Order order1 = new OrderImpl(menuItem1,12,"Someone serving",20);
        Order order2 = new OrderImpl(menuItem2,13,"Someone else", 16);
        Order order3 = new OrderImpl(menuItem2,13,"Someone else", 18);
        Order order4 = new OrderImpl(menuItem2,13,"Someone else", 12);

        Order[] orderss = {order1, order2, order3, order4};
        List<Order> orders = Arrays.asList(orderss);


        ChefController.addOrders(orders);

        launch(args);
    }
}