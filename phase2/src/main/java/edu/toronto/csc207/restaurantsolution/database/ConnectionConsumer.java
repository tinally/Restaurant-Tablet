package edu.toronto.csc207.restaurantsolution.database;

import java.sql.Connection;
import java.sql.SQLException;

@FunctionalInterface
public interface ConnectionConsumer {
  public void accept(Connection connection) throws SQLException;
}
