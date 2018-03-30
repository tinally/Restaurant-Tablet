package edu.toronto.csc207.restaurantsolution.database;

import edu.toronto.csc207.restaurantsolution.model.implementations.UserAccountImpl;
import edu.toronto.csc207.restaurantsolution.model.interfaces.UserAccount;
import org.mindrot.jbcrypt.BCrypt;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Represents the database of UserAccount for the user interface.
 */
public class AccountDatabase extends SqlLibrary {
  public AccountDatabase(DataSource dataSource) {
    super(dataSource);
    this.createTable();
  }

  @Override
  protected void createTable() {
    this.executeUpdate(connection -> {
      Statement statement = connection.createStatement();
      statement.executeUpdate("CREATE TABLE IF NOT EXISTS users (" +
          "username TEXT PRIMARY KEY," +
          "displayname TEXT," +
          "password TEXT" +
          ")");
      statement.executeUpdate("CREATE TABLE IF NOT EXISTS users_permissions (username TEXT, permission TEXT)");
    });
  }

  /**
   * Creates an account with username, displayname and password.
   *
   * @param username    the username of this account
   * @param displayname the name for display of this account
   * @param password    the password associated with this account
   */
  public void createAccount(String username, String displayname, String password) {
    String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
    this.executeUpdate(connection -> {
      PreparedStatement ps = connection.prepareStatement("INSERT OR REPLACE INTO users VALUES (?, ?, ?)");
      ps.setString(1, username);
      ps.setString(2, displayname);
      ps.setString(3, hashed);
      ps.execute();
    });
  }

  /**
   * Adds permission for the user with username.
   *
   * @param username   the username of the user
   * @param permission the permission to be added for the user
   */
  public void addPermission(String username, String permission) {
    this.executeUpdate(connection -> {
      PreparedStatement ps = connection.prepareStatement("INSERT OR REPLACE INTO users_permissions VALUES (?, ?)");
      ps.setString(1, username);
      ps.setString(2, permission);
      ps.execute();
    });
  }

  /**
   * Removes permission for the user with username.
   *
   * @param username   the username of the user
   * @param permission the permission to be removed for the user
   */
  public void removePermission(String username, String permission) {
    this.executeUpdate(connection -> {
      PreparedStatement ps = connection.prepareStatement("DELETE FROM users_permissions WHERE username = ? AND permission = ?");
      ps.setString(1, username);
      ps.setString(2, permission);
      ps.execute();
    });
  }

  /**
   * Retrieves the account with username.
   *
   * @param username the username of the account to be retrieved
   * @return the account retrieved
   */
  public UserAccount retrieveAccount(String username) {
    final ArrayList<String> perms = new ArrayList<>();
    return this.executeQuery(connection -> {
      PreparedStatement ps = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
      ps.setString(1, username);
      PreparedStatement permissionsPS = connection.prepareStatement("SELEcT * FROM users_permissions WHERE username = ?");
      permissionsPS.setString(1, username);
      ResultSet permRs = permissionsPS.executeQuery();
      while (permRs.next()) {
        perms.add(permRs.getString("permission"));
      }

      ResultSet userRs = ps.executeQuery();
      if (userRs.next()) {
        UserAccount user = new UserAccountImpl();
        user.setUsername(userRs.getString("username"));
        user.setDisplayName(userRs.getString("displayname"));
        user.setPermissions(perms);
        return user;
      }
      return null;
    });
  }

  /**
   * Verifies the account with username and password.
   *
   * @param username the username of this account
   * @param password the password associated with this account
   * @return true if the account is verified; false otherwise
   */
  public boolean verifyAccount(String username, String password) {
    return this.executeQuery(connection -> {
      PreparedStatement ps = connection.prepareStatement("SELECT password FROM users WHERE username = ?");
      ps.setString(1, username);
      ResultSet userRs = ps.executeQuery();
      if (userRs.next()) {
        boolean passwordOK = BCrypt.checkpw(password, userRs.getString("password"));
        return passwordOK;
      } else {
        return false;
      }
    });
  }
}
