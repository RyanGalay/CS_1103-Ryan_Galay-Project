
/**
 * Write a description of class Supplier here.
 *
 * Ryan Galay
 * Apr 7 2026
 */
public class Supplier
{
    // initialize variables
    int supplier_id;
    String company_name;
    int category_id;
    
    // constructor
    public Supplier(int supplier_id, String company_name, int category_id) {
        this.supplier_id = supplier_id;
        this.company_name = company_name;
        this.category_id = category_id;
    }

    // Getters

    public int getSupplierId() {
        return supplier_id;
    }

    public String getCompanyName() {
        return company_name;
    }

    public int getCategoryId() {
        return category_id;
    }

    // Setters

    public void setSupplierId(int supplier_id) {
        this.supplier_id = supplier_id;
    }

    public void setCompanyName(String company_name) {
        this.company_name = company_name;
    }

    public void setCategoryId(int category_id) {
        this.category_id = category_id;
    }
}