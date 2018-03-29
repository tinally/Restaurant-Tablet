package edu.toronto.csc207.restaurantsolution.remoting.server;

import edu.toronto.csc207.restaurantsolution.database.*;
import edu.toronto.csc207.restaurantsolution.model.implementations.OrderImpl;
import edu.toronto.csc207.restaurantsolution.model.interfaces.*;
import edu.toronto.csc207.restaurantsolution.remoting.DataManager;
import edu.toronto.csc207.restaurantsolution.remoting.client.RemoteListener;
import org.sqlite.SQLiteDataSource;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Backend data manager implementation that manages database interactions and serves as the "master"
 * data model of the distributed RMI application.
 *
 * Handles remote client requests for data and notifies registered remote listeners of data
 * updates.
 */
public final class DataServer implements DataManager {
  private final AccountDatabase accountDatabase;
  private final BillRecordDatabase billRecordDatabase;
  private final IngredientDatabase ingredientDatabase;
  private final InventoryDatabase inventoryDatabase;
  private final MenuItemDatabase menuItemDatabase;
  private final OrderDatabase orderDatabase;
  private ArrayList<RemoteListener> listeners;
  private InfoLogger logger;

  /** Constructs a new data server. */
  // TODO: Add logging as specified
  public DataServer() {
    listeners = new ArrayList<>();
    SQLiteDataSource dataSource = new SQLiteDataSource();
    dataSource.setUrl("jdbc:sqlite:restaurant.db");
    accountDatabase = new AccountDatabase(dataSource);
    menuItemDatabase = new MenuItemDatabase(dataSource);
    ingredientDatabase = new IngredientDatabase(dataSource);
    orderDatabase = new OrderDatabase(dataSource, menuItemDatabase, ingredientDatabase);
    billRecordDatabase = new BillRecordDatabase(dataSource, orderDatabase);
    inventoryDatabase = new InventoryDatabase(dataSource);
    accountDatabase.createAccount("admin", "Administrator", "admin");
    accountDatabase.addPermission("admin","view.server");
    accountDatabase.addPermission("admin","view.chef");
    accountDatabase.addPermission("admin","view.receiver");
    accountDatabase.addPermission("admin","view.cashier");
    accountDatabase.addPermission("admin","view.manager");
    logger = new InfoLogger("DataServer", "log.txt");
  }

  /**
   * Registers a remote update listener that will be notified of data updates.
   *
   * @param listener the remote listener that will handle updates.
   */
  @Override
  public void registerListener(RemoteListener listener) {
    listeners.add(listener);
  }

  /**
   * Notifies all registered listeners of a data update.
   */
  @Override
  public void updateListeners() {
    try {
      for (RemoteListener listener : listeners) {
        listener.update();
      }
    } catch (RemoteException e) {
      logger.printError("Critical network error - reboot and reconnect clients..." + e);
    }
  }

  @Override
  public UserAccount getUserAccount(String username) {
    return accountDatabase.retrieveAccount(username);
  }

  @Override
  public BillRecord getBillRecord(UUID billRecordId) {
    return billRecordDatabase.getBillRecord(billRecordId);
  }

  @Override
  public List<BillRecord> getAllBills() {
    return billRecordDatabase.retrieveAllBills();
  }

  @Override
  public void modifyBillRecord(BillRecord billRecord) {
    billRecordDatabase.addOrUpdateBill(billRecord);
    updateListeners();
  }

  @Override
  public Ingredient getIngredient(String ingredientName) {
    return ingredientDatabase.getIngredient(ingredientName);
  }

  @Override
  public List<Ingredient> getAllIngredients() {
    return ingredientDatabase.getAllIngredient();
  }

  @Override
  public void setIngredientCount(Ingredient ingredient, Integer ingredientCount) {
    logger.printInfo("Inventory of " + ingredient + " was set to " + ingredientCount);
    ingredientDatabase.registerIngredient(ingredient);
    inventoryDatabase.setIngredientCount(ingredient, ingredientCount);
    updateListeners();
  }

  @Override
  public int getIngredientCount(Ingredient ingredient) {
    return inventoryDatabase.getIngredientCount(ingredient);
  }

  @Override
  public MenuItem getMenuItem(String name) {
    return menuItemDatabase.getMenuItem(name);
  }

  @Override
  public List<MenuItem> getAllMenuItems() {
    return menuItemDatabase.getAllMenuItems();
  }

  @Override
  public void modifyOrder(Order order) {
    orderDatabase.insertOrUpdateOrder(order);
    logger.printInfo("Order " + order.getOrderID() + " was " + order.getOrderStatus());
    updateListeners();
  }

  @Override
  public void modifyOrder(Order order, OrderStatus newstatus) {
    ((OrderImpl) order).setOrderStatus(newstatus);
    orderDatabase.insertOrUpdateOrder(order);
    logger.printInfo("Order " + order.getOrderID() + " was " + order.getOrderStatus());
    updateListeners();
  }

  @Override
  public Order getOrder(UUID orderId) {
    return orderDatabase.retrieveOrder(orderId);
  }

  @Override
  public List<Order> getAllOrders() {
    return orderDatabase.retrieveAllOrders();
  }

  @Override
  public boolean checkLogin(String username, String password) {
    return this.accountDatabase.verifyAccount(username, password);
  }
}
