package edu.toronto.csc207.restaurantsolution.remoting.server;

import edu.toronto.csc207.restaurantsolution.database.*;
import edu.toronto.csc207.restaurantsolution.model.interfaces.*;
import edu.toronto.csc207.restaurantsolution.remoting.DataManager;
import edu.toronto.csc207.restaurantsolution.remoting.client.RemoteListener;
import org.sqlite.SQLiteDataSource;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.*;

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
  private final IngredientLibrary ingredientLibrary;
  private final InventoryDatabase inventoryDatabase;
  private final MenuItemLibrary menuItemLibrary;
  private final OrderDatabase orderDatabase;
  private ArrayList<RemoteListener> listeners;

  /** Constructs a new data server. */
  public DataServer() {
    listeners = new ArrayList<>();
    SQLiteDataSource dataSource = new SQLiteDataSource();
    dataSource.setUrl("jdbc:sqlite:restaurant.db");
    accountDatabase = new AccountDatabase(dataSource);
    menuItemLibrary = new MenuItemLibrary(dataSource);
    ingredientLibrary = new IngredientLibrary(dataSource);
    orderDatabase = new OrderDatabase(dataSource, menuItemLibrary, ingredientLibrary);
    billRecordDatabase = new BillRecordDatabase(dataSource, orderDatabase);
    inventoryDatabase = new InventoryDatabase(dataSource);
    accountDatabase.createAccount("admin", "Administrator", "admin");
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
  public void updateListeners() throws RemoteException {
    for (RemoteListener listener : listeners) {
      listener.update();
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
  }

  @Override
  public Ingredient getIngredient(String ingredientName) {
    return ingredientLibrary.getIngredient(ingredientName);
  }

  @Override
  public List<Ingredient> getAllIngredients() {
    return ingredientLibrary.getAllIngredient();
  }

  @Override
  public void setIngredientCount(Ingredient ingredient, Integer ingredientCount) {
    inventoryDatabase.setIngredientCount(ingredient, ingredientCount);
  }

  @Override
  public int getIngredientCount(Ingredient ingredient) {
    return inventoryDatabase.getIngredientCount(ingredient);
  }

  @Override
  public MenuItem getMenuItem(String name) {
    return menuItemLibrary.getMenuItem(name);
  }

  @Override
  public List<MenuItem> getAllMenuItems() {
    return menuItemLibrary.getAllMenuItems();
  }

  @Override
  public void modifyOrder(Order order) {
    orderDatabase.insertOrUpdateOrder(order);
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
