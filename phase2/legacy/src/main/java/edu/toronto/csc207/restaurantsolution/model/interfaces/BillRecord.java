package edu.toronto.csc207.restaurantsolution.model.interfaces;


import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface BillRecord {
  UUID getBillID();
  List<Order> getBilledOrders();
  Double getChargedSubtotal();
  Double getChargedTax();
  Double getChargedGratuity();
  Double getPaidAmount();
  Date getBilledDate();
}
