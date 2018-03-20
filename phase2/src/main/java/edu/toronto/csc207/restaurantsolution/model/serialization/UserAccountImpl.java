package edu.toronto.csc207.restaurantsolution.model.serialization;

import edu.toronto.csc207.restaurantsolution.model.interfaces.UserAccount;

import java.util.List;
import java.util.UUID;

public class UserAccountImpl implements UserAccount {
  private List<String> permissions;
  private String username;
  private String displayName;


  @Override
  public List<String> getPermissions() {
    return this.permissions;
  }

  @Override
  public String getUsername() {
    return this.username;
  }

  @Override
  public String getDisplayName() {
    return this.displayName;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPermissions(List<String> permissions) {
    this.permissions = permissions;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }
}
