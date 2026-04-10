
/**
 * Write a description of class DatabaseManager here.
 *
 * Ryan Galay
 * Apr 8 2026
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
    static String url = "jdbc:sqlite:inventory.db";

    public static void initializeDatabase() {
        // 1. Define the array locally right where you need it
        String[] sqlStatements = {
                "CREATE TABLE IF NOT EXISTS Category (category_id INTEGER PRIMARY KEY, category_name TEXT);",
                "CREATE TABLE IF NOT EXISTS Employees (employee_id INTEGER PRIMARY KEY, full_name TEXT, title TEXT, salary REAL, permissions TEXT);",
                "CREATE TABLE IF NOT EXISTS Product (product_id INTEGER PRIMARY KEY, name TEXT, price REAL, category_id INTEGER, FOREIGN KEY(category_id) REFERENCES Category(category_id));",
                "CREATE TABLE IF NOT EXISTS Supplier (supplier_id INTEGER PRIMARY KEY, company_name TEXT, category_id INTEGER, FOREIGN KEY(category_id) REFERENCES Category(category_id));",
                "CREATE TABLE IF NOT EXISTS Transactions (transaction_id INTEGER PRIMARY KEY, employee_id INTEGER, total_cost REAL, timestamps DATE, FOREIGN KEY(employee_id) REFERENCES Employees(employee_id));",
                "CREATE TABLE IF NOT EXISTS Stocking (order_id INTEGER PRIMARY KEY, supplier_id INTEGER, quantity INTEGER, day DATE, product_id INTEGER, FOREIGN KEY(product_id) REFERENCES Product(product_id));",
                "CREATE TABLE IF NOT EXISTS TransactionItem (transaction_id INTEGER, product_id INTEGER, quantity INTEGER, FOREIGN KEY(transaction_id) REFERENCES Transactions(transaction_id), FOREIGN KEY(product_id) REFERENCES Product(product_id));"
        };

        // 2. Open connection and execute in one go
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {

            // Turn on Foreign Key enforcement
            stmt.execute("PRAGMA foreign_keys = ON;");

            // Execute each statement
            for (String sql : sqlStatements) {
                stmt.execute(sql);
            }
            System.out.println("Database tables initialized successfully.");

        } catch (SQLException e) {
            System.out.println("Initialization error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}