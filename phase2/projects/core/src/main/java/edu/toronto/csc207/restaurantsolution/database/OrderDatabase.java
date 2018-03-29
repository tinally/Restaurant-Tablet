package edu.toronto.csc207.restaurantsolution.database;

import edu.toronto.csc207.restaurantsolution.model.implementations.OrderImpl;
import edu.toronto.csc207.restaurantsolution.model.interfaces.Ingredient;
import edu.toronto.csc207.restaurantsolution.model.interfaces.Order;
import edu.toronto.csc207.restaurantsolution.model.interfaces.OrderStatus;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.*;

/**
 * Represents the database of Orders.
 */
public final class OrderDatabase extends SqlLibrary {

  /**
   * The database of MenuItems.
   */
  private final MenuItemDatabase menuItems;

  /**
   * The database of Ingredients.
   */
  private final IngredientDatabase ingredientDatabase;

  /**
   * Constructs an OrderDatabase with dataSource, menuItems and ingredientDatabase.
   *
   * @param dataSource         database source
   * @param menuItems          the database of MenuItems
   * @param ingredientDatabase the database of Ingredients
   */
  public OrderDatabase(DataSource dataSource,
                       MenuItemDatabase menuItems,
                       IngredientDatabase ingredientDatabase) {
    super(dataSource);
    this.menuItems = menuItems;
    this.ingredientDatabase = ingredientDatabase;
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
          "createdUsername TEXT," +
          "orderStatus TEXT)");
      statement.executeUpdate("CREATE TABLE IF NOT EXISTS orders_removals (" +
          "orderId TEXT PRIMARY KEY," +
          "ingredient TEXT)");
      statement.executeUpdate("CREATE TABLE IF NOT EXISTS orders_additions (" +
          "orderId TEXT PRIMARY KEY," +
          "ingredient TEXT," +
          "count INTEGER)");
    });
  }

  /**
   * Inserts or updates order in this database.
   *
   * @param order the Order to be inserted or updated
   */
  public void insertOrUpdateOrder(Order order) {
    this.executeUpdate(connection -> {
      PreparedStatement orderPs = connection.prepareStatement("INSERT OR REPLACE INTO orders VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
      orderPs.setString(1, order.getOrderID().toString());
      orderPs.setInt(2, order.getTableNumber());
      orderPs.setInt(3, order.getOrderNumber());
      orderPs.setString(4, order.getMenuItem().getName());
      orderPs.setDouble(5, order.getOrderCost());
      orderPs.setTimestamp(6, Timestamp.from(order.getOrderTimestamp()));
      orderPs.setString(7, order.getCreatingUser());
      orderPs.setString(8, order.getOrderStatus().toString());

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

  /**
   * Retrieves the Order with orderID.
   *
   * @param orderID the UUID of the Order to be retrieved
   * @return the Order with orderID
   */
  public Order retrieveOrder(UUID orderID) {
    //todo: optimize this to use a single connection instead of multiple db hits.
    return this.executeQuery(connection -> {
      PreparedStatement orderPs = connection.prepareStatement("SELECT * FROM orders WHERE orderId = ?");
      orderPs.setString(1, orderID.toString());
      PreparedStatement removalsPs = connection.prepareStatement("SELECT * from orders_removals WHERE orderId = ?");
      removalsPs.setString(1, orderID.toString());
      PreparedStatement additionsPs = connection.prepareStatement("SELECT * from orders_additions WHERE orderId = ?");
      additionsPs.setString(1, orderID.toString());


      ResultSet removalsResult = removalsPs.executeQuery();
      final List<Ingredient> removals = new ArrayList<>();
      while (removalsResult.next()) {
        String ingredientName = removalsResult.getString("ingredient");
        removals.add(this.ingredientDatabase.getIngredient(ingredientName));
      }

      final HashMap<Ingredient, Integer> additions = new HashMap<>();
      ResultSet additionsResult = additionsPs.executeQuery();
      while (additionsResult.next()) {
        String ingredientName = additionsResult.getString("ingredient");
        additions.put(this.ingredientDatabase.getIngredient(ingredientName), additionsResult.getInt("count"));
      }

      ResultSet orderResult = orderPs.executeQuery();

      if (orderResult.next()) {
        Order order = new OrderImpl();
        order.setMenuItem(this.menuItems.getMenuItem(orderResult.getString("menuItem")));
        order.setOrderDate(orderResult.getTimestamp("orderTimestamp").toInstant());
        order.setCreatingUser(orderResult.getString("createdUsername"));
        order.setOrderId(UUID.fromString(orderResult.getString("orderId")));
        order.setTableNumber(orderResult.getInt("tableNumber"));
        order.setOrderNumber(orderResult.getInt("orderNumber"));
        order.setOrderCost(orderResult.getDouble("orderCost"));
        order.setOrderStatus(OrderStatus.valueOf(orderResult.getString("orderStatus")));
        order.setRemovals(removals);
        order.setAdditions(additions);
        return order;
      }
      return null;
    });
  }

  /**
   * Retrieves all Orders in this database.
   *
   * @return the List of Orders
   */
  public List<Order> retrieveAllOrders() {
    //todo: optimize this
    final ArrayList<Order> orders = new ArrayList<>();
    this.executeUpdate(connection -> {
      Statement statement = connection.createStatement();
      ResultSet orderResults = statement.executeQuery("SELECT orderId from orders");
      while (orderResults.next()) {
        orders.add(this.retrieveOrder(UUID.fromString(orderResults.getString("orderId"))));
      }
    });
    return orders;
  }
}
