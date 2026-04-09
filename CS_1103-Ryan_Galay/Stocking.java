
/**
 * Write a description of class Stocking here.
 *
 * Ryan Galay
 * Apr 7 2026
 */
public class Stocking
{
    // initialize variables
    int order_id;
    int supplier_id;
    int quantity;
    String day;
    int product_id;
    
    // Constructor
    public Stocking(int order_id, int supplier_id, int quantity, String day, int product_id) {
        this.order_id = order_id;
        this.supplier_id = supplier_id;
        this.quantity = quantity;
        this.day = day;
        this.product_id = product_id;
    }

    // getters

    public int getOrderId() {
        return order_id;
    }

    public int getSupplierId() {
        return supplier_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getDay() {
        return day;
    }

    public int getProductId() {
        return product_id;
    }

    // Setters

    public void setOrderId(int order_id) {
        this.order_id = order_id;
    }

    public void setSupplierId(int supplier_id) {
        this.supplier_id = supplier_id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setProductId(int product_id) {
        this.product_id = product_id;
    }
}