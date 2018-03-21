package edu.toronto.csc207.restaurantsolution.database;

import edu.toronto.csc207.restaurantsolution.model.interfaces.Ingredient;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class InventoryDatabase extends SqlLibrary {
  protected InventoryDatabase(DataSource dataSource) {
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

  public void setIngredientCount(Ingredient ingredient, Integer ingredientCount) {
    this.executeUpdate(connection -> {
      PreparedStatement ps = connection.prepareStatement("INSERT OR REPLACE INTO inventory VALUES (?, ?)");
      ps.setString(1, ingredient.getName());
      ps.setInt(2, ingredientCount);
      ps.execute();
    });
  }

  public int getIngredientCount(Ingredient ingredient) {
    return this.executeQuery(connection -> {
      PreparedStatement ps = connection.prepareStatement("SELECT value from inventory WHERE ingredient = ?");
      ps.setString(1, ingredient.getName());
      ResultSet rs = ps.executeQuery();
      if (rs.next()) {
        return rs.getInt("value");
      }
      return 0;
    });
  }
}
