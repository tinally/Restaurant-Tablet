package edu.toronto.csc207.restaurantsolution.remoting.server;

import edu.toronto.csc207.restaurantsolution.database.AccountDatabase;
import edu.toronto.csc207.restaurantsolution.model.interfaces.UserAccount;
import edu.toronto.csc207.restaurantsolution.remoting.DataManager;
import edu.toronto.csc207.restaurantsolution.remoting.client.RemoteListener;
import org.sqlite.SQLiteDataSource;

import java.rmi.RemoteException;
import java.util.ArrayList;
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
  private ArrayList<RemoteListener> listeners;

  /** Constructs a new data server. */
  public DataServer() {
    listeners = new ArrayList<>();
    SQLiteDataSource dataSource = new SQLiteDataSource();
    dataSource.setUrl("jdbc:sqlite:restaurant.db");
    accountDatabase = new AccountDatabase(dataSource);
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
  public void updateListeners() throws RemoteException {
    for (RemoteListener listener : listeners) {
      listener.update();
    }
  }

  public UserAccount getUserAccount(String username) {
    return accountDatabase.retrieveAccount(username);
  }

  @Override
  public boolean checkLogin(String username, String password) {
    return this.accountDatabase.verifyAccount(username, password);
  }
}
