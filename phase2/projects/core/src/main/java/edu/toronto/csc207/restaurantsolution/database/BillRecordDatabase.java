package edu.toronto.csc207.restaurantsolution.database;

import edu.toronto.csc207.restaurantsolution.model.implementations.BillRecordImpl;
import edu.toronto.csc207.restaurantsolution.model.implementations.OrderImpl;
import edu.toronto.csc207.restaurantsolution.model.interfaces.BillRecord;
import edu.toronto.csc207.restaurantsolution.model.interfaces.Ingredient;
import edu.toronto.csc207.restaurantsolution.model.interfaces.Order;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public final class BillRecordDatabase extends SqlLibrary {

  private final OrderDatabase orderDatabase;

  protected BillRecordDatabase(DataSource dataSource, OrderDatabase orderDatabase) {
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
          "chargedGratiuty REAL," +
          "chargedTax REAL," +
          "paidAmount REAL," +
          "billedTimestamp INTEGER" +
          ")");

      statement.executeUpdate("CREATE TABLE IF NOT EXISTS bills_orders (billId TEXT, orderId TEXT)");
    });
  }

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

  public BillRecord getBillRecord(UUID billRecordID) {
    //todo: optimize this to use a single connection instead of multiple db hits.
    return this.executeQuery(connection -> {
      PreparedStatement billPs = connection.prepareStatement("SELECT * FROM bills WHERE billId = ?");
      billPs.setString(1, billRecordID.toString());
      PreparedStatement orderPs = connection.prepareStatement("SELECT * FROM bills_orders WHERE billId = ?");
      billPs.setString(1, billRecordID.toString());


      ResultSet ordersResult = orderPs.executeQuery();
      final List<Order> orders = new ArrayList<>();
      while (ordersResult.next()) {
        orders.add(this.orderDatabase.retrieveOrder(UUID.fromString(ordersResult.getString("orderId"))));
      }

      ResultSet billResult = billPs.executeQuery();
      if (billResult.next()) {
        BillRecordImpl billRecord = new BillRecordImpl();
        billRecord.setBillID(UUID.fromString(billResult.getString("billId")));
        billRecord.setBilledDate(billResult.getTimestamp("billedTimestamp").toInstant());
        billRecord.setChargedGratuity(billResult.getDouble("chargedGratuity"));
        billRecord.setChargedTax(billResult.getDouble("chargedTax"));
        billRecord.setChargedSubtotal(billResult.getDouble("chargedSubtotal"));
        billRecord.setBilledOrders(orders);
        return billRecord;
      }
      return null;
    });
  }
  public List<BillRecord> retrieveAllBills() {
    //todo: optimize this
    final ArrayList<BillRecord> bills = new ArrayList<>();
    this.executeUpdate(connection -> {
      Statement statement = connection.createStatement();
      ResultSet billResults = statement.executeQuery("SELECT billId from bills");
      while(billResults.next()) {
        bills.add(this.getBillRecord(UUID.fromString(billResults.getString("billId"))));
      }
    });
    return bills;
  }
}
