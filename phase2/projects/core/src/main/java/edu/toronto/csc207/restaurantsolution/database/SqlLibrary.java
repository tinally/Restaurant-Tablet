package edu.toronto.csc207.restaurantsolution.database;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public abstract class SqlLibrary {

  private final DataSource dataSource;

  SqlLibrary(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  protected abstract void createTable();

  final void executeUpdate(ConnectionConsumer connectionExecutable) {
    Connection connection = null;
    try {
      connection = dataSource.getConnection();
      connectionExecutable.accept(connection);
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (connection != null) {
          connection.close();
        }
      } catch (SQLException e) {
        // connection close failed.
        e.printStackTrace();
      }
    }
  }

  final <T> T executeQuery(ConnectionFunction<T> connectionExecutable) {
    Connection connection = null;
    try {
      connection = dataSource.getConnection();
      return connectionExecutable.call(connection);
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      // note that this finally block always runs.
      try {
        if (connection != null) {
          connection.close();
        }
      } catch (SQLException e) {
        // connection close failed.
        e.printStackTrace();
      }
    }
    return null;
  }
}
