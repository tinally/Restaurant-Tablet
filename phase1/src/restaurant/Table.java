package restaurant;

public class Table {
    private int tableNumber;
    private int numCustomers;
//    private Server server;

    public Table(int tableNumber, int numCustomers){
        this.tableNumber = tableNumber;
        this.numCustomers = numCustomers;

    }

    public int getNumCustomers() {
        return numCustomers;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setNumCustomers(int numCustomers) {
        this.numCustomers = numCustomers;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }
}
