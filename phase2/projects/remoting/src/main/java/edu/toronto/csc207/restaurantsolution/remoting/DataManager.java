package edu.toronto.csc207.restaurantsolution.remoting;

import edu.toronto.csc207.restaurantsolution.model.interfaces.*;
import edu.toronto.csc207.restaurantsolution.remoting.server.UpdateServer;

import java.rmi.RemoteException;
import java.util.List;

/**
 * A remote data manager to be used by synchronized clients of the distributed RMI application in
 * updating and distributing data.
 */
public interface DataManager extends UpdateServer {
  List<BillRecord> getAllBills() throws RemoteException;

  void modifyBillRecord(BillRecord billRecord) throws RemoteException;

  Ingredient getIngredient(String ingredientName) throws RemoteException;

  void registerIngredient(Ingredient ingredient) throws RemoteException;

  List<Ingredient> getAllIngredients() throws RemoteException;

  void setIngredientCount(Ingredient ingredient, Integer ingredientCount) throws RemoteException;

  int getIngredientCount(Ingredient ingredient) throws RemoteException;

  List<MenuItem> getAllMenuItems() throws RemoteException;

  UserAccount getUserAccount(String username) throws RemoteException;

  void modifyOrder(Order order) throws RemoteException;

  void modifyOrder(Order order, OrderStatus newstatus) throws RemoteException;

  void modifyMenuItem(MenuItem m) throws RemoteException;

  List<Order> getAllOrders() throws RemoteException;

  boolean checkLogin(String username, String password) throws RemoteException;

  void createAccount(String username, String password, String displayName, List<String> permissions) throws RemoteException;
}
