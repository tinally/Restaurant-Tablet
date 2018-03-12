package edu.toronto.csc207.restaurantsolution.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.toronto.csc207.restaurantsolution.data.Ingredient;
import edu.toronto.csc207.restaurantsolution.data.MenuItem;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Order represents the order from each table in this restaurant.
 * This class is serializable to JSON or YAML.
 */
public class Order {
  /**
   * The name of the server of this order.
   */
  @JsonProperty("serverName")
  private String serverName;

  /**
   * The table number of the table that sends this Order.
   */
  @JsonProperty("tableNumber")
  private int tableNumber;

  /**
   * The number of this order.
   */
  @JsonProperty("orderNumber")
  private int orderNumber;

  /**
   * The MenuItem the customer ordered.
   */
  @JsonProperty("menuItem")
  private MenuItem menuItem;

  /**
   * A HashMap showing how much each Ingredient should be added.
   */
  @JsonProperty("addIngredients")
  private HashMap<Ingredient, Integer> addIngredients;

  /**
   * An ArrayList showing the unwanted Ingredients from the MenuItem.
   */
  @JsonProperty("removedIngredients")
  private ArrayList<Ingredient> removedIngredients;

  /**
   * The status of this order.
   */
  @JsonProperty("status")
  private OrderStatus status;

  /**
   * Private empty constructor used for deserialization from
   * events file.
   */
  private Order() {
  }

  /**
   * Class constructor specifying the list of OrderItems, the table number, and the server of this Order.
   *
   * @param menuItem    the menu item being ordered
   * @param tableNumber the table number of the table that sends this Order
   */
  public Order(MenuItem menuItem,
               int tableNumber,
               String serverName,
               int orderNumber) {
    this.tableNumber = tableNumber;
    this.menuItem = menuItem;
    this.status = OrderStatus.CREATED;
    this.serverName = serverName;
    this.orderNumber = orderNumber;
  }

  /**
   * Returns the table number of the table that sends this Order.
   *
   * @return the table number
   */
  public int getTableNumber() {
    return tableNumber;
  }

  /**
   * Returns the current status of this Order.
   *
   * @return the status of this
   */
  public OrderStatus getStatus() {
    return status;
  }

  /**
   * Changes the status of this Order.
   *
   * @param status the status to be set
   */
  public void setStatus(OrderStatus status) {
    this.status = status;
  }

  /**
   * Returns the unique order number of this Order.
   *
   * @return the order number of this
   */
  public int getOrderNumber() {
    return orderNumber;
  }

  /**
   * Returns the menu item corresponding to this Order.
   *
   * @return the MenuItem of this
   */
  public MenuItem getMenuItem() {
    return menuItem;
  }

  /**
   * Modifies the menu item corresponding to this Order.
   *
   * @param menuItem new menu item to be set
   */
  // TODO: Use in phase 2
  public void setMenuItem(MenuItem menuItem) {
    this.menuItem = menuItem;
  }

  /**
   * Returns the name of the Server responsible for this Order.
   *
   * @return name of the Server
   */
  public String getServerName() {
    return serverName;
  }

  /**
   * @param add Ingredient to be added
   */
  // TODO: Use in phase 2
  public void addIngredients(HashMap<Ingredient, Integer> add) {
    for (HashMap.Entry<Ingredient, Integer> pair : add.entrySet()) {
      addIngredient(pair.getKey(), pair.getValue());
    }
  }

  /**
   * Add one type of Ingredient at a time
   *
   * @param ingredient the Ingredient to be added
   * @param quantity   the amount of that Ingredient to be added
   */
  private void addIngredient(Ingredient ingredient, int quantity) {
    addIngredients.put(ingredient, quantity);
    double extraPrice = quantity * ingredient.getPricing();
    menuItem.increasePrice(extraPrice);
  }

  /**
   * @param unwantedIngredient Ingredient to be removed
   */
  // TODO: Use in phase 2
  public void removeIngredient(Ingredient unwantedIngredient) {
    removedIngredients.add(unwantedIngredient);
  }

  /**
   * @return Ingredients to be removed.
   */
  // TODO: Use in phase 2
  public ArrayList<Ingredient> getRemovedIngredients() {
    return removedIngredients;
  }

  /**
   * @return Ingredients to be added..
   */
  // TODO: Use in phase 2
  public HashMap<Ingredient, Integer> getAddIngredients() {
    return addIngredients;
  }

}
