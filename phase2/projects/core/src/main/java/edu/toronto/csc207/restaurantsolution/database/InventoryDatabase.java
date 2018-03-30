package edu.toronto.csc207.restaurantsolution.database;

import edu.toronto.csc207.restaurantsolution.model.interfaces.Ingredient;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Represents the database of the current inventory of Ingredients.
 */
public class InventoryDatabase extends SqlLibrary {
  /**
   * Constructs an InventoryDatabase with dataSource.
   *
   * @param dataSource database source
   */
  public InventoryDatabase(DataSource dataSource) {
    super(dataSource);
    this.createTable();
  }

  @Override
  protected void createTable() {
    this.executeUpdate(connection -> {
      Statement statement = connection.createStatement();
      statement.executeUpdate("CREATE TABLE IF NOT EXISTS inventory (ingredient TEXT PRIMARY KEY, value INTEGER)");
    });
  }

  /**
   * Sets the amount of ingredient to ingredientCount.
   *
   * @param ingredient      the Ingredient to be updated
   * @param ingredientCount the new amount of ingredient
   */
  public void setIngredientCount(Ingredient ingredient, Integer ingredientCount) {
    this.executeUpdate(connection -> {
      PreparedStatement ps = connection.prepareStatement("INSERT OR REPLACE INTO inventory VALUES (?, ?)");
      ps.setString(1, ingredient.getName());
      ps.setInt(2, ingredientCount);
      ps.execute();
    });
  }

  /**
   * Returns the amount of ingredient in inventory.
   *
   * @param ingredient the Ingredient to be checked
   * @return the amount of ingredient in inventory
   */
  public int getIngredientCount(Ingredient ingredient) {
    return this.executeQuery(connection -> {
      try {
        PreparedStatement ps = connection.prepareStatement("SELECT value from inventory WHERE ingredient = ?");
        ps.setString(1, ingredient.getName());
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
          return rs.getInt("value");
        }
        return 0;
      } catch (NullPointerException e) {
        return 0;
      }
    });
  }
}
