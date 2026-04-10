
/**
 * Write a description of class TransactionItem here.
 *
 * Ryan Galay
 * Apr 7 2026
 */
public class TransactionItem
{
    // initialize variables
    int transaction_id;
    int product_id;
    int quantity;
    
    // Constructor
    public TransactionItem(int transaction_id, int product_id, int quantity) {
        this.transaction_id = transaction_id;
        this.product_id = product_id;
        this.quantity = quantity;
    }

    // Getter

    public int getTransactionId() {
        return transaction_id;
    }

    public int getProductId() {
        return product_id;
    }

    public int getQuantity() {
        return quantity;
    }

    // Setters

    public void setTransactionId(int transaction_id) {
        this.transaction_id = transaction_id;
    }

    public void setProductId(int product_id) {
        this.product_id = product_id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}