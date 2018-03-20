package tests;


import com.j256.ormlite.stmt.query.In;
import edu.toronto.csc207.restaurantsolution.database.AccountDatabase;
import edu.toronto.csc207.restaurantsolution.database.IngredientLibrary;
import edu.toronto.csc207.restaurantsolution.database.MenuItemLibrary;
import edu.toronto.csc207.restaurantsolution.model.interfaces.Ingredient;
import edu.toronto.csc207.restaurantsolution.model.interfaces.MenuItem;
import edu.toronto.csc207.restaurantsolution.model.interfaces.UserAccount;
import edu.toronto.csc207.restaurantsolution.model.serialization.IngredientImpl;
import edu.toronto.csc207.restaurantsolution.model.serialization.MenuItemImpl;
import org.junit.Assert;
import org.junit.Test;
import org.sqlite.SQLiteDataSource;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class DatabaseTest {

  @Test
  public void testInitIngredientDatabase() {
    SQLiteDataSource ds = new SQLiteDataSource();
    ds.setUrl("jdbc:sqlite:test.db");
    IngredientLibrary l = new IngredientLibrary(ds);
  }

  @Test
  public void retrieveIngredient() {
    SQLiteDataSource ds = new SQLiteDataSource();
    ds.setUrl("jdbc:sqlite:test.db");
    IngredientLibrary l = new IngredientLibrary(ds);

    IngredientImpl ingredient = new IngredientImpl();
    ingredient.setName("Test Ingredient");
    ingredient.setCost(5.99);
    ingredient.setDefaultReorderAmount(10);
    ingredient.setPricing(10.0);
    ingredient.setReorderThreshold(15);

    IngredientImpl ingredientTwo = new IngredientImpl();
    ingredientTwo.setName("Test Ingredient Two");
    ingredientTwo.setCost(5.99);
    ingredientTwo.setDefaultReorderAmount(10);
    ingredientTwo.setPricing(10.0);
    ingredientTwo.setReorderThreshold(15);

    l.registerIngredient(ingredient);
    l.registerIngredient(ingredientTwo);

    Ingredient retrieved = l.getIngredient("Test Ingredient");
    Ingredient retrievedTwo = l.getIngredient("Test Ingredient Two");

    Assert.assertEquals("Test Ingredient", retrieved.getName());
    Assert.assertNotEquals("Test Ingredient", retrievedTwo.getName());
  }

  @Test
  public void retrieveAllIngredient() {
    SQLiteDataSource ds = new SQLiteDataSource();
    ds.setUrl("jdbc:sqlite:test.db");
    IngredientLibrary l = new IngredientLibrary(ds);

    IngredientImpl ingredient = new IngredientImpl();
    ingredient.setName("Test Ingredient");
    ingredient.setCost(5.99);
    ingredient.setDefaultReorderAmount(10);
    ingredient.setPricing(10.0);
    ingredient.setReorderThreshold(15);

    IngredientImpl ingredientTwo = new IngredientImpl();
    ingredientTwo.setName("Test Ingredient Two");
    ingredientTwo.setCost(5.99);
    ingredientTwo.setDefaultReorderAmount(10);
    ingredientTwo.setPricing(10.0);
    ingredientTwo.setReorderThreshold(15);

    l.registerIngredient(ingredient);
    l.registerIngredient(ingredientTwo);

    List<Ingredient> ingredients = l.getAllIngredient();

    Assert.assertFalse(ingredients.isEmpty());
  }


  @Test
  public void retrieveMenuItem() {
    SQLiteDataSource ds = new SQLiteDataSource();
    ds.setUrl("jdbc:sqlite:test.db");
    IngredientLibrary l = new IngredientLibrary(ds);
    MenuItemLibrary ml = new MenuItemLibrary(ds);

    IngredientImpl ingredient = new IngredientImpl();
    ingredient.setName("Test Ingredient");
    ingredient.setCost(5.99);
    ingredient.setDefaultReorderAmount(10);
    ingredient.setPricing(10.0);
    ingredient.setReorderThreshold(15);

    IngredientImpl ingredientTwo = new IngredientImpl();
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
    Assert.assertFalse(retrieved.getIngredientRequirements().isEmpty());
  }


  @Test
  public void retrieveAllMenuItem() {
    SQLiteDataSource ds = new SQLiteDataSource();
    ds.setUrl("jdbc:sqlite:test.db");
    IngredientLibrary l = new IngredientLibrary(ds);
    MenuItemLibrary ml = new MenuItemLibrary(ds);

    IngredientImpl ingredient = new IngredientImpl();
    ingredient.setName("Test Ingredient");
    ingredient.setCost(5.99);
    ingredient.setDefaultReorderAmount(10);
    ingredient.setPricing(10.0);
    ingredient.setReorderThreshold(15);

    IngredientImpl ingredientTwo = new IngredientImpl();
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

    Assert.assertFalse(ml.getAllMenuItems().isEmpty());
  }

  @Test
  public void testUserAccountCreation() {
    SQLiteDataSource ds = new SQLiteDataSource();
    ds.setUrl("jdbc:sqlite:test.db");
    AccountDatabase ad = new AccountDatabase(ds);
    ad.createAccount("TestAccount", "Test Account", "password");
    UserAccount ua = ad.retrieveAccount("TestAccount");
    Assert.assertEquals("TestAccount", ua.getUsername());
  }

  @Test
  public void testUserAccountPasswordValidation() {
    SQLiteDataSource ds = new SQLiteDataSource();
    ds.setUrl("jdbc:sqlite:test.db");
    AccountDatabase ad = new AccountDatabase(ds);
    ad.createAccount("TestAccount", "Test Account", "password");
    Assert.assertTrue(ad.verifyAccount("TestAccount", "password"));
  }

  @Test
  public void testUserAccountPermissions() {
    SQLiteDataSource ds = new SQLiteDataSource();
    ds.setUrl("jdbc:sqlite:test.db");
    AccountDatabase ad = new AccountDatabase(ds);
    ad.createAccount("TestAccount", "Test Account", "password");
    ad.addPermission("TestAccount", "view.server");
    UserAccount ua = ad.retrieveAccount("TestAccount");
    Assert.assertEquals("view.server", ua.getPermissions().get(0));
    ad.removePermission("TestAccount", "view.server");
    ua = ad.retrieveAccount("TestAccount");
    Assert.assertTrue(ua.getPermissions().isEmpty());
  }


}
