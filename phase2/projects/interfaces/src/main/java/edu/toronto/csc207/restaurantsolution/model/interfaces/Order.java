package edu.toronto.csc207.restaurantsolution.model.interfaces;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Represents an Order in the restaurant.
 */
public interface Order extends Serializable {
    /**
     * Returns an unique ID for an Order.
     *
     * @return an unique ID for an Order.
     */
    UUID getOrderID();

    /**
     * Returns the table number of this Order.
     *
     * @return the table number of this Order.
     */
    Integer getTableNumber();

    /**
     * Returns the order number of this Order.
     *
     * @return the order number of this Order.
     */
    Integer getOrderNumber();

    /**
     * Return the MenuItem corresponding to this Order.
     *
     * @return the corresponding MenuItem
     */
    MenuItem getMenuItem();

    /**
     * Returns the list of Ingredients to be removed from this Order.
     *
     * @return the list of Ingredients
     */
    List<Ingredient> getRemovals();

    /**
     * Returns the map of Ingredients added with the corresponding amount.
     *
     * @return the map of Ingredient and Integer
     */
    Map<Ingredient, Integer> getAdditions();

    /**
     * Returns the cost of this Order.
     *
     * @return the cost of this Order.
     */
    Double getOrderCost();

    /**
     * Returns the time the Order was created.
     *
     * @return the time the Order was created.
     */
    Instant getOrderTimestamp();

    /**
     * Returns the name of the User who created this Order.
     *
     * @return the name of the User who created this Order.
     */
    String getCreatingUser();
}
