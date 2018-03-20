package GUI.Chef;

import edu.toronto.csc207.restaurantsolution.data.Ingredient;
import edu.toronto.csc207.restaurantsolution.data.MenuItem;
import edu.toronto.csc207.restaurantsolution.model.Order;
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

public class ChefGUI extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        URL src = new File("src/main/java/GUI.Chef/GUI.Chef.fxml").toURL();
        Parent root = FXMLLoader.load(src);
        primaryStage.setTitle("GUI.Chef Screen");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        Ingredient ing1 = new Ingredient("Burger", 25.5, 55.6,20);
        Ingredient ing2 = new Ingredient("Tomato", 3.2,5.6,20);
        Ingredient ing3 = new Ingredient("BLT", 3.2,5.6,20);
        Ingredient ing4 = new Ingredient("JellyFish", 3.2,5.6,20);

        HashMap<Ingredient, Integer> ingredients = new HashMap<>();
        ingredients.put(ing1, 3);
        ingredients.put(ing2, 4);
        ingredients.put(ing3,5);
        ingredients.put(ing4, 2);

        MenuItem menuItem1 = new MenuItem("Burger Sandwich", ingredients,25.5 );
        MenuItem menuItem2 = new MenuItem("Second Burger", ingredients, 24.5);

        Order order1 = new Order(menuItem1,12,"Someone serving",20);
        Order order2 = new Order(menuItem2,13,"Someone else", 16);
        Order order3 = new Order(menuItem2,13,"Someone else", 18);
        Order order4 = new Order(menuItem2,13,"Someone else", 12);

        Order[] orderss = {order1, order2, order3, order4};
        List<Order> orders = Arrays.asList(orderss);

        ChefController.addOrders(orders);

        launch(args);
    }
}