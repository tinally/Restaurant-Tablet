package edu.toronto.csc207.restaurantsolution.model.interfaces;

import java.util.List;

public interface UserAccount {
  List<String> getPermissions();
  String getUsername();
  String getDisplayName();
}
