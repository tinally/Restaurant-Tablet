package edu.toronto.csc207.restaurantsolution.database;

import edu.toronto.csc207.restaurantsolution.model.interfaces.Ingredient;
import edu.toronto.csc207.restaurantsolution.model.serialization.IngredientImpl;
import org.intellij.lang.annotations.Language;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Function;

public final class IngredientLibrary extends SqlLibrary {
  public IngredientLibrary(DataSource dataSource) {
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

        IngredientImpl ingredient = new IngredientImpl();
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

        IngredientImpl ingredient = new IngredientImpl();
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
