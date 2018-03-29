import edu.toronto.csc207.restaurantsolution.database.AccountDatabase;
import edu.toronto.csc207.restaurantsolution.database.IngredientDatabase;
import edu.toronto.csc207.restaurantsolution.database.MenuItemDatabase;
import edu.toronto.csc207.restaurantsolution.database.OrderDatabase;
import edu.toronto.csc207.restaurantsolution.model.interfaces.Ingredient;
import edu.toronto.csc207.restaurantsolution.model.interfaces.MenuItem;
import edu.toronto.csc207.restaurantsolution.model.interfaces.Order;
import edu.toronto.csc207.restaurantsolution.model.interfaces.UserAccount;
import edu.toronto.csc207.restaurantsolution.model.implementations.IngredientImpl;
import edu.toronto.csc207.restaurantsolution.model.implementations.MenuItemImpl;
import edu.toronto.csc207.restaurantsolution.model.implementations.OrderImpl;
import org.junit.jupiter.api.Test;
import org.sqlite.SQLiteDataSource;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseTest {

  @Test
  public void testInitIngredientDatabase() {
    SQLiteDataSource ds = new SQLiteDataSource();
    ds.setUrl("jdbc:sqlite:test.db");
    IngredientDatabase l = new IngredientDatabase(ds);
  }

  @Test
  public void retrieveIngredient() {
    SQLiteDataSource ds = new SQLiteDataSource();
    ds.setUrl("jdbc:sqlite:test.db");
    IngredientDatabase l = new IngredientDatabase(ds);

    Ingredient ingredient = new IngredientImpl();
    ingredient.setName("Test Ingredient");
    ingredient.setCost(5.99);
    ingredient.setDefaultReorderAmount(10);
    ingredient.setPricing(10.0);
    ingredient.setReorderThreshold(15);

    Ingredient ingredientTwo = new IngredientImpl();
    ingredientTwo.setName("Test Ingredient Two");
    ingredientTwo.setCost(5.99);
    ingredientTwo.setDefaultReorderAmount(10);
    ingredientTwo.setPricing(10.0);
    ingredientTwo.setReorderThreshold(15);

    l.registerIngredient(ingredient);
    l.registerIngredient(ingredientTwo);

    Ingredient retrieved = l.getIngredient("Test Ingredient");
    Ingredient retrievedTwo = l.getIngredient("Test Ingredient Two");

    assertEquals("Test Ingredient", retrieved.getName());
    assertNotEquals("Test Ingredient", retrievedTwo.getName());
  }

  @Test
  public void retrieveAllIngredient() {
    SQLiteDataSource ds = new SQLiteDataSource();
    ds.setUrl("jdbc:sqlite:test.db");
    IngredientDatabase l = new IngredientDatabase(ds);

    Ingredient ingredient = new IngredientImpl();
    ingredient.setName("Test Ingredient");
    ingredient.setCost(5.99);
    ingredient.setDefaultReorderAmount(10);
    ingredient.setPricing(10.0);
    ingredient.setReorderThreshold(15);

    Ingredient ingredientTwo = new IngredientImpl();
    ingredientTwo.setName("Test Ingredient Two");
    ingredientTwo.setCost(5.99);
    ingredientTwo.setDefaultReorderAmount(10);
    ingredientTwo.setPricing(10.0);
    ingredientTwo.setReorderThreshold(15);

    l.registerIngredient(ingredient);
    l.registerIngredient(ingredientTwo);

    List<Ingredient> ingredients = l.getAllIngredient();

    assertFalse(ingredients.isEmpty());
  }


  @Test
  public void retrieveMenuItem() {
    SQLiteDataSource ds = new SQLiteDataSource();
    ds.setUrl("jdbc:sqlite:test.db");
    IngredientDatabase l = new IngredientDatabase(ds);
    MenuItemDatabase ml = new MenuItemDatabase(ds);

    Ingredient ingredient = new IngredientImpl();
    ingredient.setName("Test Ingredient");
    ingredient.setCost(5.99);
    ingredient.setDefaultReorderAmount(10);
    ingredient.setPricing(10.0);
    ingredient.setReorderThreshold(15);

    Ingredient ingredientTwo = new IngredientImpl();
    ingredientTwo.setName("Test Ingredient Two");
    ingredientTwo.setCost(5.99);
    ingredientTwo.setDefaultReorderAmount(10);
    ingredientTwo.setPricing(10.0);
    ingredientTwo.setReorderThreshold(15);

    l.registerIngredient(ingredient);
    l.registerIngredient(ingredientTwo);

    HashMap<Ingredient, Integer> usage = new HashMap<>();
    usage.put(ingredient, 1);
    usage.put(ingredientTwo, 2);

    MenuItemImpl menuItem = new MenuItemImpl();
    menuItem.setPrice(100d);
    menuItem.setName("Toast Sandwich");
    menuItem.setIngredientRequirements(usage);

    ml.registerMenuItem(menuItem);

    MenuItem retrieved = ml.getMenuItem("Toast Sandwich");
    assertFalse(retrieved.getIngredientRequirements().isEmpty());
  }


  @Test
  public void retrieveAllMenuItem() {
    SQLiteDataSource ds = new SQLiteDataSource();
    ds.setUrl("jdbc:sqlite:test.db");
    IngredientDatabase l = new IngredientDatabase(ds);
    MenuItemDatabase ml = new MenuItemDatabase(ds);

    Ingredient ingredient = new IngredientImpl();
    ingredient.setName("Test Ingredient");
    ingredient.setCost(5.99);
    ingredient.setDefaultReorderAmount(10);
    ingredient.setPricing(10.0);
    ingredient.setReorderThreshold(15);

    Ingredient ingredientTwo = new IngredientImpl();
    ingredientTwo.setName("Test Ingredient Two");
    ingredientTwo.setCost(5.99);
    ingredientTwo.setDefaultReorderAmount(10);
    ingredientTwo.setPricing(10.0);
    ingredientTwo.setReorderThreshold(15);

    l.registerIngredient(ingredient);
    l.registerIngredient(ingredientTwo);

    HashMap<Ingredient, Integer> usage = new HashMap<>();
    usage.put(ingredient, 1);
    usage.put(ingredientTwo, 2);

    MenuItemImpl menuItem = new MenuItemImpl();
    menuItem.setPrice(100d);
    menuItem.setName("Toast Sandwich");
    menuItem.setIngredientRequirements(usage);

    ml.registerMenuItem(menuItem);

    assertFalse(ml.getAllMenuItems().isEmpty());
  }

  @Test
  public void testAddOrder() {
    SQLiteDataSource ds = new SQLiteDataSource();
    ds.setUrl("jdbc:sqlite:test.db");
    IngredientDatabase l = new IngredientDatabase(ds);
    MenuItemDatabase ml = new MenuItemDatabase(ds);
    OrderDatabase od = new OrderDatabase(ds, ml, l);

    Ingredient ingredient = new IngredientImpl();
    ingredient.setName("Test Ingredient");
    ingredient.setCost(5.99);
    ingredient.setDefaultReorderAmount(10);
    ingredient.setPricing(10.0);
    ingredient.setReorderThreshold(15);

    Ingredient ingredientTwo = new IngredientImpl();
    ingredientTwo.setName("Test Ingredient Two");
    ingredientTwo.setCost(5.99);
    ingredientTwo.setDefaultReorderAmount(10);
    ingredientTwo.setPricing(10.0);
    ingredientTwo.setReorderThreshold(15);

    l.registerIngredient(ingredient);
    l.registerIngredient(ingredientTwo);

    HashMap<Ingredient, Integer> usage = new HashMap<>();
    usage.put(ingredient, 1);
    usage.put(ingredientTwo, 2);

    MenuItemImpl menuItem = new MenuItemImpl();
    menuItem.setPrice(100d);
    menuItem.setName("Toast Sandwich");
    menuItem.setIngredientRequirements(usage);

    ml.registerMenuItem(menuItem);

    Order order = new OrderImpl();
    order.setAdditions(new HashMap<>());
    order.setRemovals(new ArrayList<>());
    order.setOrderId(UUID.randomUUID());
    order.setMenuItem(menuItem);
    order.setOrderCost(10d);
    order.setTableNumber(15);
    order.setOrderNumber(11);
    order.setOrderDate(Instant.now());
    order.setCreatingUser("TestUser");

    od.insertOrUpdateOrder(order);
    assertFalse(od.retrieveAllOrders().isEmpty());

  }

  @Test
  public void testUserAccountCreation() {
    SQLiteDataSource ds = new SQLiteDataSource();
    ds.setUrl("jdbc:sqlite:test.db");
    AccountDatabase ad = new AccountDatabase(ds);
    ad.createAccount("TestAccount", "Test Account", "password");
    UserAccount ua = ad.retrieveAccount("TestAccount");
    assertEquals("TestAccount", ua.getUsername());
  }

  @Test
  public void testUserAccountPasswordValidation() {
    SQLiteDataSource ds = new SQLiteDataSource();
    ds.setUrl("jdbc:sqlite:test.db");
    AccountDatabase ad = new AccountDatabase(ds);
    ad.createAccount("TestAccount", "Test Account", "password");
    assertTrue(ad.verifyAccount("TestAccount", "password"));
  }

  @Test
  public void testUserAccountPermissions() {
    SQLiteDataSource ds = new SQLiteDataSource();
    ds.setUrl("jdbc:sqlite:test.db");
    AccountDatabase ad = new AccountDatabase(ds);
    ad.createAccount("TestAccount", "Test Account", "password");
    ad.addPermission("TestAccount", "view.server");
    UserAccount ua = ad.retrieveAccount("TestAccount");
    assertEquals("view.server", ua.getPermissions().get(0));
    ad.removePermission("TestAccount", "view.server");
    ua = ad.retrieveAccount("TestAccount");
    assertTrue(ua.getPermissions().isEmpty());
  }


}
