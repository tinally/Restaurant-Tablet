package edu.toronto.csc207.restaurantsolution.database;

import edu.toronto.csc207.restaurantsolution.model.implementations.BillRecordImpl;
import edu.toronto.csc207.restaurantsolution.model.interfaces.BillRecord;
import edu.toronto.csc207.restaurantsolution.model.interfaces.Order;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Represents the database of BillRecord.
 */
public final class BillRecordDatabase extends SqlLibrary {

  /**
   * The database of Order.
   */
  private final OrderDatabase orderDatabase;

  /**
   * Constructs a BillRecordDatabase with dataSource and orderDatabase.
   *
   * @param dataSource    database source
   * @param orderDatabase database of Order
   */
  public BillRecordDatabase(DataSource dataSource, OrderDatabase orderDatabase) {
    super(dataSource);
    this.orderDatabase = orderDatabase;
    this.createTable();
  }

  @Override
  protected void createTable() {
    this.executeUpdate(connection -> {
      Statement statement = connection.createStatement();
      statement.executeUpdate("CREATE TABLE IF NOT EXISTS bills (" +
          "billId TEXT PRIMARY KEY," +
          "chargedSubtotal REAL," +
          "chargedGratuity REAL," +
          "chargedTax REAL," +
          "paidAmount REAL," +
          "billedTimestamp INTEGER" +
          ")");

      statement.executeUpdate("CREATE TABLE IF NOT EXISTS bills_orders (billId TEXT, orderId TEXT)");
    });
  }

  /**
   * Updates or adds billRecord to the database.
   *
   * @param billRecord the BillRecord to be updated or added
   */
  public void addOrUpdateBill(BillRecord billRecord) {
    this.executeUpdate(connection -> {
      PreparedStatement billPs = connection.prepareStatement("INSERT OR REPLACE INTO bills VALUES (?, ?, ?, ?, ? ,?)");
      billPs.setString(1, billRecord.getBillID().toString());
      billPs.setDouble(2, billRecord.getChargedSubtotal());
      billPs.setDouble(3, billRecord.getChargedGratuity());
      billPs.setDouble(4, billRecord.getChargedTax());
      billPs.setDouble(5, billRecord.getPaidAmount());
      billPs.setTimestamp(6, Timestamp.from(billRecord.getBilledDate()));

      PreparedStatement orderPs = connection.prepareStatement("INSERT INTO bills_orders VALUES (?, ?)");
      for (Order o : billRecord.getBilledOrders()) {
        orderPs.setString(1, billRecord.getBillID().toString());
        orderPs.setString(2, o.getOrderID().toString());
        orderPs.addBatch();
      }
      orderPs.executeBatch();
      billPs.execute();
    });
  }

  /**
   * Returns the BillRecord with billRecordID.
   *
   * @param billRecordID the UUID of the BillRecord to be returned
   * @return the BillRecord with billRecordID
   */
  public BillRecord getBillRecord(UUID billRecordID) {
    //todo: optimize this to use a single connection instead of multiple db hits.
    return this.executeQuery(connection -> {
      PreparedStatement billPs = connection.prepareStatement("SELECT * FROM bills WHERE billId = ?");
      billPs.setString(1, billRecordID.toString());
      PreparedStatement orderPs = connection.prepareStatement("SELECT * FROM bills_orders WHERE billId = ?");
      orderPs.setString(1, billRecordID.toString());


      ResultSet ordersResult = orderPs.executeQuery();
      final List<Order> orders = new ArrayList<>();
      while (ordersResult.next()) {
        orders.add(this.orderDatabase.retrieveOrder(UUID.fromString(ordersResult.getString("orderId"))));
      }

      ResultSet billResult = billPs.executeQuery();
      if (billResult.next()) {
        BillRecord billRecord = new BillRecordImpl();
        billRecord.setBillID(UUID.fromString(billResult.getString("billId")));
        billRecord.setBilledDate(billResult.getTimestamp("billedTimestamp").toInstant());
        billRecord.setChargedGratuity(billResult.getDouble("chargedGratuity"));
        billRecord.setChargedTax(billResult.getDouble("chargedTax"));
        billRecord.setChargedSubtotal(billResult.getDouble("chargedSubtotal"));
        billRecord.setBilledOrders(orders);
        billRecord.setPaidAmount(billResult.getDouble("paidAmount"));
        return billRecord;
      }
      return null;
    });
  }

  /**
   * Retrieves all BillRecords in this database.
   *
   * @return the List of BillRecord
   */
  public List<BillRecord> retrieveAllBills() {
    final ArrayList<BillRecord> bills = new ArrayList<>();
    this.executeUpdate(connection -> {
      Statement statement = connection.createStatement();
      ResultSet billResults = statement.executeQuery("SELECT billId from bills");
      while (billResults.next()) {
        bills.add(this.getBillRecord(UUID.fromString(billResults.getString("billId"))));
      }
    });
    return bills;
  }
}
