package edu.toronto.csc207.restaurantsolution.remoting;

import edu.toronto.csc207.restaurantsolution.model.interfaces.*;
import edu.toronto.csc207.restaurantsolution.remoting.server.UpdateServer;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.UUID;

/**
 * A remote data manager to be used by synchronized clients of the distributed RMI application in
 * updating and distributing data.
 */
public interface DataManager extends UpdateServer {
  /*
   * As a sub-interface of Remote, every method specified in this interface must be able to throw a
   * RemoteException in the case of remote errors.
   */
  BillRecord getBillRecord(UUID billRecordId) throws RemoteException;

  List<BillRecord> getAllBills() throws RemoteException;

  void modifyBillRecord(BillRecord billRecord) throws RemoteException;

  Ingredient getIngredient(String ingredientName) throws RemoteException;

  List<Ingredient> getAllIngredients() throws RemoteException;

  void setIngredientCount(Ingredient ingredient, Integer ingredientCount) throws RemoteException;

  int getIngredientCount(Ingredient ingredient) throws RemoteException;

  MenuItem getMenuItem(String name) throws RemoteException;

  List<MenuItem> getAllMenuItems() throws RemoteException;

  UserAccount getUserAccount(String username) throws RemoteException;

  void modifyOrder(Order order) throws RemoteException;

  void modifyOrder(Order order, OrderStatus newstatus) throws RemoteException;

  Order getOrder(UUID orderId) throws RemoteException;

  List<Order> getAllOrders() throws RemoteException;

  boolean checkLogin(String username, String password) throws RemoteException;
}
