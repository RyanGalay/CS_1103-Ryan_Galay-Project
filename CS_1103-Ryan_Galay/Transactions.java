
/**
 * Write a description of class Transaction here.
 *
 * Ryan Galay
 * Apr 7 2026
 */
public class Transactions
{
    // initialize variables
    int transaction_id;
    int employee_id;
    double total_cost;
    String timestamps;
    
    // Constructor
    public Transactions(int transaction_id, int employee_id, double total_cost, String timestamps) {
        this.transaction_id = transaction_id;
        this.employee_id = employee_id;
        this.total_cost = total_cost;
        this.timestamps = timestamps;
    }

    // Getters

    public int getTransactionId() {
        return transaction_id;
    }

    public int getEmployeeId() {
        return employee_id;
    }

    public double getTotalCost() {
        return total_cost;
    }

    public String getTimestamps() {
        return timestamps;
    }

    // Setters

    public void setTransactionId(int transaction_id) {
        this.transaction_id = transaction_id;
    }

    public void setEmployeeId(int employee_id) {
        this.employee_id = employee_id;
    }

    public void setTotalCost(double total_cost) {
        this.total_cost = total_cost;
    }

    public void setTimestamps(String timestamps) {
        this.timestamps = timestamps;
    }
}