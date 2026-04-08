
/**
 * Write a description of class Transaction here.
 *
 * Ryan Galay
 * Apr 7 2026
 */
public class Transaction
{
    // initialize variables
    int transaction_id;
    int employee_id;
    double total_cost;
    String timestamp;
    
    // Constructor
    public Transaction(int transaction_id, int employee_id, double total_cost, String timestamp) {
        this.transaction_id = transaction_id;
        this.employee_id = employee_id;
        this.total_cost = total_cost;
        this.timestamp = timestamp;
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

    public String getTimestamp() {
        return timestamp;
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

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}