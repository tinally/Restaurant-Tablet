package edu.toronto.csc207.restaurantsolution.database;

import java.sql.Connection;
import java.sql.SQLException;

@FunctionalInterface
public interface ConnectionFunction<T> {
  T call(Connection connection) throws SQLException;
}
