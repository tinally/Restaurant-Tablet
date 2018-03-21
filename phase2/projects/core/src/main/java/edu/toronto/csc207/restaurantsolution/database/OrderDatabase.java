package edu.toronto.csc207.restaurantsolution.database;

import edu.toronto.csc207.restaurantsolution.model.interfaces.Ingredient;
import edu.toronto.csc207.restaurantsolution.model.interfaces.Order;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Map;

public final class OrderDatabase extends SqlLibrary {
  public OrderDatabase(DataSource dataSource) {
    super(dataSource);
    this.createTable();
  }

  @Override
  protected void createTable() {
    this.executeUpdate(connection -> {
      Statement statement = connection.createStatement();
      statement.executeUpdate("CREATE TABLE IF NOT EXISTS orders (" +
          "orderId TEXT PRIMARY KEY," +
          "tableNumber INTEGER," +
          "orderNumber INTEGER," +
          "menuItem TEXT," +
          "orderCost DOUBLE," +
          "orderTimestamp INTEGER," +
          "createdUsername TEXT)");
      statement.executeUpdate("CREATE TABLE IF NOT EXISTS orders_removals (" +
          "orderId TEXT PRIMARY KEY," +
          "ingredient TEXT)");
      statement.executeUpdate("CREATE TABLE IF NOT EXISTS orders_additions (" +
          "orderId TEXT PRIMARY KEY," +
          "ingredient TEXT," +
          "count INTEGER)");
    });
  }

  public void insertOrUpdateOrder(Order order) {
    this.executeUpdate(connection -> {
      PreparedStatement orderPs = connection.prepareStatement("INSERT OR REPLACE INTO orders VALUES (?, ?, ?, ?, ?, ?, ?)");
      orderPs.setString(1, order.getOrderID().toString());
      orderPs.setInt(2, order.getTableNumber());
      orderPs.setInt(3, order.getOrderNumber());
      orderPs.setString(4, order.getMenuItem().getName());
      orderPs.setDouble(5, order.getOrderCost());
      orderPs.setTimestamp(6, Timestamp.from(order.getOrderTimestamp()));
      orderPs.setString(7, order.getCreatingUser());

      //todo: drop all orders_removals rows
      PreparedStatement removalsPs = connection.prepareStatement("INSERT OR REPLACE INTO orders_removals VALUES(?,?)");
      for (Ingredient i : order.getRemovals()) {
        removalsPs.setString(1, order.getOrderID().toString());
        removalsPs.setString(2, i.getName());
        removalsPs.addBatch();
      }

      PreparedStatement additionsPs = connection.prepareStatement("INSERT OR REPLACE INTO orders_additions VALUES(?,?,?)");
      for (Map.Entry<Ingredient, Integer> i : order.getAdditions().entrySet()) {
        additionsPs.setString(1, order.getOrderID().toString());
        additionsPs.setString(2, i.getKey().getName());
        additionsPs.setInt(3, i.getValue());
        additionsPs.addBatch();
      }
      removalsPs.executeBatch();
      additionsPs.executeBatch();
      orderPs.execute();
    });
  }
}
