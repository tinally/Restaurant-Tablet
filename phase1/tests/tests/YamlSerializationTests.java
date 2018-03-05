package tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import kitchen.Ingredient;
import kitchen.Order;
import org.junit.Test;
import restaurant.MenuItem;
import restaurant.OrderItem;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class YamlSerializationTests {
  @Test
  public void testOrderSerializable() {
    ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

    Ingredient ingredient = new Ingredient("Tomato", 10d, 1d);
    HashMap<Ingredient, Integer> ingredientAmount = new HashMap<>();
    ingredientAmount.put(ingredient, 2);
    MenuItem tomatoSandwich = new MenuItem("Tomato Sandwich", ingredientAmount, 20d);
    OrderItem orderItem = new OrderItem(tomatoSandwich);

    Order order = new Order(Arrays.asList(orderItem), 15);
    try {
      String yaml = mapper.writeValueAsString(ingredient);
      System.out.println(yaml);
      System.out.println(mapper.writeValueAsString(tomatoSandwich));

    } catch (JsonProcessingException e) {


    }


  }
}