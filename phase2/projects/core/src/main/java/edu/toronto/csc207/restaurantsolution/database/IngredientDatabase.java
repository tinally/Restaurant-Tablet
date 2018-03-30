package edu.toronto.csc207.restaurantsolution.database;

import edu.toronto.csc207.restaurantsolution.model.implementations.IngredientImpl;
import edu.toronto.csc207.restaurantsolution.model.interfaces.Ingredient;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the database of all the existing Ingredients.
 */
public final class IngredientDatabase extends SqlLibrary {
  /**
   * Constructs an IngredientDatabase with dataSource.
   *
   * @param dataSource database source
   */
  public IngredientDatabase(DataSource dataSource) {
    super(dataSource);
    this.createTable();
  }

  @Override
  protected void createTable() {
    this.executeUpdate(connection -> {
      Statement statement = connection.createStatement();
      statement.executeUpdate("CREATE TABLE IF NOT EXISTS ingredients" +
          " (name TEXT PRIMARY KEY," +
          "  cost REAL," +
          "  pricing REAL," +
          "  reorderThreshold INTEGER," +
          "  defaultReorderAmount INTEGER)");
    });
  }

  /**
   * Registers ingredient to this database.
   *
   * @param ingredient the Ingredient to be registered
   */
  public void registerIngredient(Ingredient ingredient) {
    this.executeUpdate(connection -> {
      PreparedStatement ps = connection.prepareStatement("INSERT OR REPLACE INTO ingredients VALUES (?, ?, ?, ?, ?)");
      ps.setString(1, ingredient.getName());
      ps.setDouble(2, ingredient.getCost());
      ps.setDouble(3, ingredient.getPricing());
      ps.setDouble(4, ingredient.getReorderThreshold());
      ps.setDouble(5, ingredient.getDefaultReorderAmount());
      ps.execute();
    });
  }

  /**
   * Returns the Ingredient with ingredientName.
   *
   * @param ingredientName the name of the Ingredient to be returned
   * @return the Ingredient with ingredientName
   */
  public Ingredient getIngredient(String ingredientName) {
    return this.executeQuery(connection -> {
      PreparedStatement ps = connection.prepareStatement("SELECT * FROM ingredients WHERE name == ?");
      ps.setString(1, ingredientName);
      ResultSet rs = ps.executeQuery();
      if (rs.next()) {
        String name = rs.getString("name");
        Double cost = rs.getDouble("cost");
        Double pricing = rs.getDouble("pricing");
        Integer reorderThreshold = rs.getInt("reorderThreshold");
        Integer defaultReorderAmount = rs.getInt("defaultReorderAmount");

        Ingredient ingredient = new IngredientImpl();
        ingredient.setName(name);
        ingredient.setCost(cost);
        ingredient.setPricing(pricing);
        ingredient.setReorderThreshold(reorderThreshold);
        ingredient.setDefaultReorderAmount(defaultReorderAmount);
        ps.close();
        return ingredient;
      }
      return null;
    });
  }

  /**
   * Returns all Ingredients in this database.
   *
   * @return the List of Ingredients
   */
  public List<Ingredient> getAllIngredient() {
    final List<Ingredient> ingredients = new ArrayList<Ingredient>();
    return this.executeQuery(connection -> {
      PreparedStatement ps = connection.prepareStatement("SELECT * FROM ingredients");
      ResultSet rs = ps.executeQuery();

      while (rs.next()) {
        String name = rs.getString("name");
        Double cost = rs.getDouble("cost");
        Double pricing = rs.getDouble("pricing");
        Integer reorderThreshold = rs.getInt("reorderThreshold");
        Integer defaultReorderAmount = rs.getInt("defaultReorderAmount");

        Ingredient ingredient = new IngredientImpl();
        ingredient.setName(name);
        ingredient.setCost(cost);
        ingredient.setPricing(pricing);
        ingredient.setReorderThreshold(reorderThreshold);
        ingredient.setDefaultReorderAmount(defaultReorderAmount);
        ingredients.add(ingredient);
      }
      return ingredients;
    });
  }
}
