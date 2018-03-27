package edu.toronto.csc207.restaurantsolution.model.interfaces;

import java.util.List;

/**
 * Represents the account for user interfaces.
 */
public interface UserAccount {
    /**
     * Returns the views the users get to access to.
     *
     * @return the list of Strings representing the views
     */
    List<String> getPermissions();

    /**
     * Returns the username of this account.
     *
     * @return the username
     */
    String getUsername();

    /**
     * Returns the name for display of this account.
     *
     * @return the name for display
     */
    String getDisplayName();
}
