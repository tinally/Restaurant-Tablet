package edu.toronto.csc207.restaurantsolution.model.implementations;

import edu.toronto.csc207.restaurantsolution.model.interfaces.BillRecord;
import edu.toronto.csc207.restaurantsolution.model.interfaces.Order;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

/**
 * Represents an implementation of BillRecord.
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
  public void setBillID(UUID billID) {
    this.billID = billID;
  }

  @Override
  public List<Order> getBilledOrders() {
    return this.billedOrders;
  }

  @Override
  public void setBilledOrders(List<Order> billedOrders) {
    this.billedOrders = billedOrders;
  }

  @Override
  public Double getChargedSubtotal() {
    return this.chargedSubtotal;
  }

  @Override
  public void setChargedSubtotal(Double chargedSubtotal) {
    this.chargedSubtotal = chargedSubtotal;
  }

  @Override
  public Double getChargedTax() {
    return this.chargedTax;
  }

  @Override
  public void setChargedTax(Double chargedTax) {
    this.chargedTax = chargedTax;
  }

  @Override
  public Double getChargedGratuity() {
    return this.chargedGratuity;
  }

  @Override
  public void setChargedGratuity(Double chargedGratuity) {
    this.chargedGratuity = chargedGratuity;
  }

  @Override
  public Double getPaidAmount() {
    return this.paidAmount;
  }

  @Override
  public void setPaidAmount(Double paidAmount) {
    this.paidAmount = paidAmount;
  }

  @Override
  public Instant getBilledDate() {
    return this.billedDate;
  }

  @Override
  public void setBilledDate(Instant billedDate) {
    this.billedDate = billedDate;
  }

  @Override
  public String toString() {
    return LocalDateTime.ofInstant(this.getBilledDate(), ZoneId.systemDefault())
        .toLocalDate().toString() + " " + getPaidAmount();
  }
}
