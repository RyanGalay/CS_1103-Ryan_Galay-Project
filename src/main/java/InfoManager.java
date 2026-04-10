import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * Write a description of class InfoManager here.
 *
 * Ryan Galay
 * Apr 8 2026
 */
public class InfoManager
{
    private static final String URL = "jdbc:sqlite:inventory.db";

    // method to create a new category
    public static void addCategory(int id, String name)
    {
        String sql = "INSERT OR IGNORE INTO Category(category_id, category_name) VALUES(?,?)";
        // create connection
        try (Connection conn = DriverManager.getConnection(URL);
             // define prepared statment
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // prepared statements
            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    // method to add a new product to the database
    public static void addProduct(int id, String name, double price, int catId)
    {
        String sql = "INSERT OR IGNORE INTO Product(product_id, name, price, category_id) VALUES(?,?,?,?)";
        // create connection
        try (Connection conn = DriverManager.getConnection(URL);
             // define prepared statment
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // prepared statements
            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setDouble(3, price);
            pstmt.setInt(4, catId);
            pstmt.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    // add a new employee
    public static void addEmployee(int employee_id, String full_name, String title, double salary, String permissions)
    {
        String sql = "INSERT OR IGNORE INTO Employees(employee_id, full_name, title, salary, permissions) VALUES(?,?,?,?,?)";
        // create connection
        try (Connection conn = DriverManager.getConnection(URL);
             // define prepared statment
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // prepared statements
            pstmt.setInt(1, employee_id);
            pstmt.setString(2, full_name);
            pstmt.setString(3, title);
            pstmt.setDouble(4, salary);
            pstmt.setString(5, permissions);
        } catch (Exception e) { e.printStackTrace(); }
    }

    // Add a new supplier
    public void addSupplier(int id, String companyName, int categoryId) {
        String sql = "INSERT OR IGNORE INTO Supplier(supplier_id, company_name, category_id) VALUES(?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.setString(2, companyName);
            pstmt.setInt(3, categoryId);

            pstmt.executeUpdate();
        } catch (Exception e) {e.printStackTrace();}
    }

    //-----------------------// data removal methods // ----------------------------------//

    // Remove a Product by ID
    public void removeProduct(int productId) {
        String sql = "DELETE FROM Product WHERE product_id = ?";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, productId);
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Product " + productId + " removed.");
            } else {
                System.out.println("No product found with ID " + productId);
            }
        } catch (Exception e) {
            System.out.println("Error removing product: " + e.getMessage());
        }
    }

    // Remove a Category
    public void removeCategory(int categoryId) {
        String sql = "DELETE FROM Category WHERE category_id = ?";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, categoryId);
            pstmt.executeUpdate();

        } catch (Exception e) {
            // makes sure that a category with products is not removed
            System.out.println("Cannot delete category: Ensure no products or suppliers are linked to it first.");
        }
    }

    // deactivation rather than removal to prevent issues with transactions
    public void setEmployeeStatus(int employeeId, boolean active) {
        // 1 means Active, 0 means Inactive
        String sql = "UPDATE Employees SET is_active = ? WHERE employee_id = ?";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, active ? 1 : 0);
            pstmt.setInt(2, employeeId);

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Employee status updated.");
            }
        } catch (Exception e) {e.printStackTrace();}
    }

    // de-activation rather than removal for same reason as employee
    public void setSupplierActiveStatus(int supplierId, boolean active) {
        String sql = "UPDATE Supplier SET is_active = ? WHERE supplier_id = ?";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, active ? 1 : 0);
            pstmt.setInt(2, supplierId);
            pstmt.executeUpdate();

            System.out.println("Supplier status updated.");
        } catch (Exception e) {e.printStackTrace();}
    }
}