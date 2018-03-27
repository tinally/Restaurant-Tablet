package edu.toronto.csc207.restaurantsolution.model.interfaces;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

/**
 * Represents a bill record for a table.
 */
public interface BillRecord extends Serializable {
    /**
     * Returns an unique ID for a bill.
     *
     * @return an unique ID for a bill.
     */
    UUID getBillID();

    /**
     * Returns List of Orders that are billed.
     *
     * @return List of Orders that are billed.
     */
    List<Order> getBilledOrders();

    /**
     * Returns the subtotal of the bill.
     *
     * @return the subtotal of the bill.
     */
    Double getChargedSubtotal();

    /**
     * Returns the tax included for the bill.
     *
     * @return the tax included for the bill.
     */
    Double getChargedTax();

    /**
     * Returns the gratuity included for the bill.
     *
     * @return the gratuity included for the bill.
     */
    Double getChargedGratuity();

    /**
     * Returns the total amount paid.
     *
     * @return the total amount paid.
     */
    Double getPaidAmount();

    /**
     * Returns the date of the bill.
     *
     * @return the date of the bill.
     */
    Instant getBilledDate();
}
