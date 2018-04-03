package edu.toronto.csc207.restaurantsolution.database;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

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
      Statement nonBlocking = connection.createStatement();
      nonBlocking.executeUpdate("PRAGMA journal_mode = WAL; PRAGMA recursive_triggers = ON; PRAGMA synchronous = NORMAL;");
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
      Statement nonBlocking = connection.createStatement();
      nonBlocking.executeUpdate("PRAGMA journal_mode = WAL; PRAGMA recursive_triggers = ON; PRAGMA synchronous = NORMAL;");
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
