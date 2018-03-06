package kitchen;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import restaurant.MenuItem;

import java.util.*;

public class Order {

  /**
   * The table number of the table that sends this Order.
   */
  private int tableNumber;

  private int orderNumber;

  /**
   * The MenuItem the customer ordered.
   */
  @JsonProperty("menuItem") private MenuItem menuItem;
  /**
   * A HashMap showing how much each Ingredient should be added.
   */
  @JsonProperty("addIngredients") private HashMap<Ingredient, Integer> addIngredients;
  /**
   * An ArrayList showing the unwanted Ingredients from the MenuItem.
   */
  @JsonProperty("removedIngredients") private ArrayList<Ingredient> removedIngredients;
    private OrderStatus status;

  /**
   * Class constructor specifying the list of OrderItems, the table number, and the server of this Order.
   *
   * @param menuItem       the menu item being ordered
   * @param tableNumber the table number of the table that sends this Order
   */
  public Order(MenuItem menuItem, int tableNumber, String serverName, int orderNumber) {
    this.tableNumber = tableNumber;
    this.menuItem = menuItem;
    this.status = OrderStatus.CREATED;
  }

  /**
   * Returns the table number of the table that sends this Order.
   *
   * @return the table number
   */
  public int getTableNumber() {
    return tableNumber;
  }

  public OrderStatus getStatus() {
    return status;
  }

  public void setStatus(OrderStatus status) {
    this.status = status;
  }

  public int getOrderNumber() {
    return orderNumber;
  }

//    /**
//     * @param add Ingredient to be added
//     */
//    public void addIngredients(HashMap<Ingredient, Integer> add) {
//        this.addIngredients = add;
//        Iterator it = add.entrySet().iterator();
//
//        while (it.hasNext()) {
//            HashMap.Entry pair = (HashMap.Entry) it.next();
//            double extraPrice = ((Integer) pair.getValue()) * ((Ingredient) pair.getKey()).getPricing();
//            menuItem.increasePrice(extraPrice);
//            //TODO: Check if this works
//        }
//    }
//
//    /**
//     * Add one type of Ingredient at a time
//     *
//     * @param ingredient the Ingredient to be added
//     * @param quantity   the amount of that Ingredient to be added
//     */
//    public void addIngredient(Ingredient ingredient, int quantity) {
//        addIngredients.put(ingredient, quantity);
//        double extraPrice = quantity * ingredient.getPricing();
//        menuItem.increasePrice(extraPrice);
//    }
//
//
//    /**
//     * @param unwantedIngredient Ingredient to be removed
//     */
//    public void removeIngredient(Ingredient unwantedIngredient) {
//        removedIngredients.add(unwantedIngredient);
//    }
//
//    /**
//     * @return Ingredients to be removed.
//     */
//    public ArrayList<Ingredient> getRemovedIngredients() {
//        return removedIngredients;
//    }
//=======
    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }
}
