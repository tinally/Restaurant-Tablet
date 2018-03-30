package edu.toronto.csc207.restaurantsolution.model.interfaces;

import java.io.Serializable;
import java.util.List;

/**
 * Represents the account for user interfaces.
 */
public interface UserAccount extends Serializable {
  /**
   * Returns the views the users get to access to.
   *
   * @return the list of Strings representing the views
   */
  List<String> getPermissions();

  void setPermissions(List<String> permissions);

  /**
   * Returns the username of this account.
   *
   * @return the username
   */
  String getUsername();

  void setUsername(String username);

  void setDisplayName(String displayName);
}
