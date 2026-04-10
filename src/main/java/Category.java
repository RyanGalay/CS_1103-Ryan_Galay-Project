
/**
 * Write a description of class Category here.
 *
 * Ryan Galay
 * Apr 7 2026
 */

public class Category
{
    // initialize variables
    int category_id;
    String category_name;
    
    // Constructor
    public Category(int category_id, String category_name) {
        this.category_id = category_id;
        this.category_name = category_name;
    }

    // Getters

    public int getCategoryId() {
        return category_id;
    }

    public String getCategoryName() {
        return category_name;
    }

    // Setters

    public void setCategoryId(int category_id) {
        this.category_id = category_id;
    }

    public void setCategoryName(String category_name) {
        this.category_name = category_name;
    }
}