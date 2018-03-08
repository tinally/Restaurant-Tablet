<<<<<<< HEAD:phase1/src/restaurant/Table.java
package restaurant;

import kitchen.Server;

public class Table {
    /**
     * The table number
     */
    private int tableNumber;
    /**
     * The number of customers on that table
     */
    private int numCustomers;
    /**
     * The Server serving this table
     */
    private Server server;

    /**
     * Class constructor specifying the tableNumber and numCustomers.
     *
     * @param tableNumber  the table number
     * @param numCustomers the number of customers at the Table
     */
    public Table(int tableNumber, int numCustomers) {
        this.tableNumber = tableNumber;
        this.numCustomers = numCustomers;
    }

    /**
     * Returns the number of customers at this Table.
     *
     * @return the number of customers on this Table
     */
    public int getNumCustomers() {
        return numCustomers;
    }

    /**
     * Returns the table number of this Table.
     *
     * @return the tableNumber of this Table
     */
    public int getTableNumber() {
        return tableNumber;
    }

    /**
     * Sets the number of customers at this Table.
     *
     * @param numCustomers the number of customers to be set.
     */
    public void setNumCustomers(int numCustomers) {
        this.numCustomers = numCustomers;
    }

    /**
     * Sets the table number of this Table.
     *
     * @param tableNumber the table number to be set
     */
    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    /**
     * Returns the Server responsible for this Table.
     *
     * @return Return the Server serving this Table
     */
    public Server getServer() {
        return server;
    }

    /**
     * Sets the Server responsible for this Table.
     *
     * @param server set to serve this Table
     */
    public void setServer(Server server) {
        this.server = server;
    }
}
=======
package edu.toronto.csc207.restaurantsolution.model;

import edu.toronto.csc207.restaurantsolution.model.Server;

public class Table {
    /**
     * The table number
     */
    private int tableNumber;
    /**
     * The number of customers on that table
     */
    private int numCustomers;
    /**
     * The Server serving this table
     */
    private Server server;

    public Table(int tableNumber, int numCustomers){
        this.tableNumber = tableNumber;
        this.numCustomers = numCustomers;
    }

    /**
     *
     * @return the number of customers on this table
     */
    public int getNumCustomers() {
        return numCustomers;
    }

    /**
     *
     * @return the tableNumber
     */
    public int getTableNumber() {
        return tableNumber;
    }

    /**
     *
     * @param numCustomers the number of customers to be set.
     */
    public void setNumCustomers(int numCustomers) {
        this.numCustomers = numCustomers;
    }

    /**
     *
     * @param tableNumber the tableNumber to be set
     */
    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    /**
     *
     * @return Return the server serving this table
     */
    public Server getServer() {
        return server;
    }
    /**
     *
     * @param server set to serve this table
     */
    public void setServer(Server server) {
        this.server = server;
    }
}
>>>>>>> 6c2ba67e6d43c990817f7455d710287af5146462:phase1/src/main/java/edu/toronto/csc207/restaurantsolution/model/Table.java
