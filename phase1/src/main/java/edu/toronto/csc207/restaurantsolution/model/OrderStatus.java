package edu.toronto.csc207.restaurantsolution.model;

/**
 * OrderStatus contains all the possible statuses of an Order.
 */
public enum OrderStatus {
    CREATED,
    INPUTTED,
    PUSHED,
    SEEN,
    FILLED,
    REJECTED,
    DELIVERED,
    RETURNED
}
