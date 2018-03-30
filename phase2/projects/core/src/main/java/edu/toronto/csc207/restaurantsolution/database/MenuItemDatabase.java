package edu.toronto.csc207.restaurantsolution.database;

import edu.toronto.csc207.restaurantsolution.model.implementations.IngredientImpl;
import edu.toronto.csc207.restaurantsolution.model.implementations.MenuItemImpl;
import edu.toronto.csc207.restaurantsolution.model.interfaces.Ingredient;
import edu.toronto.csc207.restaurantsolution.model.interfaces.MenuItem;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents the database of MenuItems.
 */
public final class MenuItemDatabase extends SqlLibrary {
  /**
   * Constructs a MenuItemDatabase with dataSource.
   *
   * @param dataSource database source
   */
  public MenuItemDatabase(DataSource dataSource) {
    super(dataSource);
    this.createTable();
  }

  @Override
  protected void createTable() {
    this.executeUpdate(connection -> {
      Statement statement = connection.createStatement();
      statement.executeUpdate("CREATE TABLE IF NOT EXISTS menu (" +
          "name TEXT PRIMARY KEY," +
          "price DOUBLE)");
      statement.executeUpdate("CREATE TABLE IF NOT EXISTS menu_ingredients (" +
          "menu_item_name TEXT," +
          "ingredient_name TEXT," +
          "usage INTEGER," +
          "PRIMARY KEY (menu_item_name, ingredient_name))");
    });
  }

  /**
   * Registers menuItem to this database.
   *
   * @param menuItem the MenuItem to be registered
   */
  public void registerMenuItem(MenuItem menuItem) {
    this.executeUpdate(connection -> {
      PreparedStatement menuPs = connection.prepareStatement("INSERT OR REPLACE INTO menu VALUES (?, ?)");
      menuPs.setString(1, menuItem.getName());
      menuPs.setDouble(2, menuItem.getPrice());
      PreparedStatement ingredientsPs = connection.prepareStatement("INSERT OR REPLACE INTO menu_ingredients" +
          " VALUES(?, ?,?)");
      for (Map.Entry<Ingredient, Integer> entries : menuItem.getIngredientRequirements().entrySet()) {
        ingredientsPs.setString(1, menuItem.getName());
        ingredientsPs.setString(2, entries.getKey().getName());
        ingredientsPs.setInt(3, entries.getValue());
        ingredientsPs.addBatch();
      }
      menuPs.execute();
      ingredientsPs.executeBatch();
      menuPs.close();
      ingredientsPs.close();
    });
  }

  /**
   * Returns the MenuItem with name.
   *
   * @param name the name of the MenuItem to be returned
   * @return the MenuItem with name
   */
  public MenuItem getMenuItem(String name) {
    Map<Ingredient, Integer> menuUsageMap = new HashMap<>();
    return this.executeQuery(connection -> {
      PreparedStatement menuPs = connection.prepareStatement("SELECT * FROM menu WHERE name = ?");
      menuPs.setString(1, name);

      PreparedStatement ingredientPs = connection.prepareStatement("SELECT * FROM menu_ingredients " +
          "JOIN ingredients ON ingredients.name = ingredient_name AND menu_item_name = ?");
      ingredientPs.setString(1, name);

      ResultSet ingredientResults = ingredientPs.executeQuery();

      while (ingredientResults.next()) {
        String ingredientName = ingredientResults.getString("name");
        Double cost = ingredientResults.getDouble("cost");
        Double pricing = ingredientResults.getDouble("pricing");
        Integer reorderThreshold = ingredientResults.getInt("reorderThreshold");
        Integer defaultReorderAmount = ingredientResults.getInt("defaultReorderAmount");
        Integer usage = ingredientResults.getInt("usage");

        Ingredient ingredient = new IngredientImpl();
        ingredient.setName(ingredientName);
        ingredient.setCost(cost);
        ingredient.setPricing(pricing);
        ingredient.setReorderThreshold(reorderThreshold);
        ingredient.setDefaultReorderAmount(defaultReorderAmount);
        menuUsageMap.put(ingredient, usage);
      }

      ResultSet menuResult = menuPs.executeQuery();
      if (menuResult.next()) {
        MenuItemImpl m = new MenuItemImpl();
        m.setIngredientRequirements(menuUsageMap);
        m.setName(menuResult.getString("name"));
        m.setPrice(menuResult.getDouble("price"));
        return m;
      }
      return null;
    });
  }

  /**
   * Returns all MenuItems in this database.
   *
   * @return the List of MenuItems
   */
  public List<MenuItem> getAllMenuItems() {
    // Todo: This implementation is very inefficient, can be improved using a JOIN in 2 queries.
    return this.executeQuery(connection -> {
      final ArrayList<MenuItem> menuItems = new ArrayList<>();
      Statement statement = connection.createStatement();
      ResultSet menuItemsRs = statement.executeQuery("SELECT name FROM menu");
      while (menuItemsRs.next()) {
        menuItems.add(this.getMenuItem(menuItemsRs.getString("name")));
      }
      return menuItems;
    });
  }
}
