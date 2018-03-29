package edu.toronto.csc207.restaurantsolution.gui.ui;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import edu.toronto.csc207.restaurantsolution.model.interfaces.MenuItem;
import edu.toronto.csc207.restaurantsolution.model.interfaces.Order;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class DeliverableOrderMapping extends RecursiveTreeObject<DeliverableOrderMapping> {
  private final IntegerProperty tableNumber;
  private final IntegerProperty orderNumber;
  private final ObjectProperty<MenuItem> menuItem;
  private final Order order;

  DeliverableOrderMapping(Integer tableNumber, Integer orderNumber, MenuItem menuItem, Order order) {
    this.tableNumber = new SimpleIntegerProperty(tableNumber);
    this.orderNumber = new SimpleIntegerProperty(orderNumber);
    this.menuItem = new SimpleObjectProperty<>(menuItem);
    this.order = order;
  }

  public IntegerProperty tableNumberProperty() {
    return tableNumber;
  }

  public IntegerProperty orderNumberProperty() {
    return orderNumber;
  }

  public ObjectProperty<MenuItem> menuItemProperty() {
    return menuItem;
  }

  public Order getOrder() {
    return order;
  }
}