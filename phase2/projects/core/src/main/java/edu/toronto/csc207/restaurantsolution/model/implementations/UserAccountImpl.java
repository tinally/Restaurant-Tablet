package edu.toronto.csc207.restaurantsolution.model.implementations;

import edu.toronto.csc207.restaurantsolution.model.interfaces.UserAccount;

import java.util.List;

/**
 * Represents an implementation of UserAccount.
 */
public class UserAccountImpl implements UserAccount {
  private List<String> permissions;
  private String username;
  private String displayName;

  @Override
  public List<String> getPermissions() {
    return this.permissions;
  }

  @Override
  public void setPermissions(List<String> permissions) {
    this.permissions = permissions;
  }

  @Override
  public String getUsername() {
    return this.username;
  }

  @Override
  public void setUsername(String username) {
    this.username = username;
  }

  @Override
  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }
}
