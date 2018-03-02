package restaurant;

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
//    private Server server;

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

//    public Server getServer() {
//        return server;
//    }
//
//    public void setServer(Server server) {
//        this.server = server;
//    }
}
