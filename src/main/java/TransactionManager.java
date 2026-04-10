import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Write a description of class TransactionManager here.
 *
 * Ryan Galay
 * Apr 9 2026
 */
public class TransactionManager
{
    private static final String URL = "jdbc:sqlite:inventory.db";

    // method to save record of transaction
    public void recordSale(int transactionId, int employeeId, double totalCost, List<int[]> items) {
        // items is a list of [product_id, quantity]
        String transactionSql = "INSERT INTO Transactions(transaction_id, employee_id, total_cost, timestamps) VALUES(?, ?, ?, CURRENT_TIMESTAMP)";
        String itemSql = "INSERT INTO TransactionItem(transaction_id, product_id, quantity) VALUES(?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL)) {
            conn.setAutoCommit(false); // Start a "Transaction" so both succeed or both fail

            try (PreparedStatement pTrans = conn.prepareStatement(transactionSql);
                 PreparedStatement pItem = conn.prepareStatement(itemSql)) {

                // 1. Insert into Transactions
                pTrans.setInt(1, transactionId);
                pTrans.setInt(2, employeeId);
                pTrans.setDouble(3, totalCost);
                pTrans.executeUpdate();

                // 2. Insert each item
                for (int[] item : items) {
                    pItem.setInt(1, transactionId);
                    pItem.setInt(2, item[0]); // product_id
                    pItem.setInt(3, item[1]); // quantity
                    pItem.executeUpdate();
                }

                conn.commit(); // Save changes
                System.out.println("Sale recorded successfully!");
            } catch (SQLException e) {
                conn.rollback(); // Undo everything if error
                throw e;
            }
        } catch (SQLException e) {
            System.out.println("Sale Error: " + e.getMessage());
        }
    }

    // method for restocking an existing product
    public void restockProduct(int orderId, int supplierId, int productId, int quantity) {
        String sql = "INSERT INTO Stocking(order_id, supplier_id, product_id, quantity, day) VALUES(?, ?, ?, ?, CURRENT_DATE)";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, orderId);
            pstmt.setInt(2, supplierId);
            pstmt.setInt(3, productId);
            pstmt.setInt(4, quantity);

            pstmt.executeUpdate();
            System.out.println("Restock order #" + orderId + " recorded.");
        } catch (SQLException e) {
            System.out.println("Restock Error: " + e.getMessage());
        }
    }

    // method to effectively remove a transaction
    public void voidTransaction(int transactionId) {
        String sql = "UPDATE Transactions SET is_void = 1 WHERE transaction_id = ?";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, transactionId);
            int rows = pstmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Transaction #" + transactionId + " has been voided.");
            }
        } catch (SQLException e) {
            System.out.println("Void Error: " + e.getMessage());
        }
    }
}