
/**
 * Write a description of class Product here.
 *
 * Ryan Galay
 * Apr 7 2026
 */
public class Product
{
    // initialize variables
    int product_id;
    String name;
    double price;
    int stock;
    int category_id;
    
    // constructor
    public Product(int product_id, String name, double price, int stock, int category_id) {
        this.product_id = product_id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.category_id = category_id;
    }

    // Getters 
    public int getProductId() {
        return product_id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public int getCategoryId() {
        return category_id;
    }

    // Setters
    public void setProductId(int product_id) {
        this.product_id = product_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setCategoryId(int category_id) {
        this.category_id = category_id;
    }
}