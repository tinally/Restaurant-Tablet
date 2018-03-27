package edu.toronto.csc207.restaurantsolution.model.implementations;

import edu.toronto.csc207.restaurantsolution.model.interfaces.BillRecord;
import edu.toronto.csc207.restaurantsolution.model.interfaces.Order;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Represents a concrete bill record by implementing BillRecord methods.
 */
public class BillRecordImpl implements BillRecord {
  private UUID billID;
  private List<Order> billedOrders;
  private Double chargedSubtotal;
  private Double chargedTax;
  private Double chargedGratuity;
  private Double paidAmount;
  private Instant billedDate;

  @Override
  public UUID getBillID() {
    return this.billID;
  }

  @Override
  public List<Order> getBilledOrders() {
    return this.billedOrders;
  }

  @Override
  public Double getChargedSubtotal() {
    return this.chargedSubtotal;
  }

  @Override
  public Double getChargedTax() {
    return this.chargedTax;
  }

  @Override
  public Double getChargedGratuity() {
    return this.chargedGratuity;
  }

  @Override
  public Double getPaidAmount() {
    return this.paidAmount;
  }

  @Override
  public Instant getBilledDate() {
    return this.billedDate;
  }

  public void setBillID(UUID billID) {
    this.billID = billID;
  }

  public void setBilledOrders(List<Order> billedOrders) {
    this.billedOrders = billedOrders;
  }

  public void setChargedSubtotal(Double chargedSubtotal) {
    this.chargedSubtotal = chargedSubtotal;
  }

  public void setChargedTax(Double chargedTax) {
    this.chargedTax = chargedTax;
  }

  public void setChargedGratuity(Double chargedGratuity) {
    this.chargedGratuity = chargedGratuity;
  }

  public void setPaidAmount(Double paidAmount) {
    this.paidAmount = paidAmount;
  }

  public void setBilledDate(Instant billedDate) {
    this.billedDate = billedDate;
  }
}
