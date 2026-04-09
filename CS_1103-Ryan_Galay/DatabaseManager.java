
/**
 * Write a description of class DatabaseManager here.
 *
 * Ryan Galay
 * Apr 8 2026
 */

import java.sql.Connection;  
import java.sql.DriverManager;   
import java.sql.PreparedStatement; 
import java.sql.ResultSet;        
import java.sql.SQLException;     
import java.sql.Statement;

public class DatabaseManager {
    // create sqlite url
    static String url = "jdbc:sqlite:inventory.db";
    static String[] createStatements;

    public static void initializeDatabase() {
        
        try {
            // This line manually "wakes up" the driver
            Class.forName("org.sqlite.JDBC", true, DatabaseManager.class.getClassLoader()); 
        } catch (ClassNotFoundException e) {
            System.out.println("Driver NOT FOUND! Check your BlueJ Libraries.");
            return; 
        }
        
        // create all 7 tables
        try (Connection conn = DriverManager.getConnection(url)) {
            System.out.println("Connection successful!");
            String[] createStatements = {
                "CREATE TABLE IF NOT EXISTS Category (category_id INTEGER PRIMARY KEY, category_name TEXT);",
                "CREATE TABLE IF NOT EXISTS Employees (employee_id INTEGER PRIMARY KEY, full_name TEXT, title TEXT, salary REAL, permissions TEXT);",
                "CREATE TABLE IF NOT EXISTS Product (product_id INTEGER PRIMARY KEY, name TEXT, price REAL, category_id INTEGER, FOREIGN KEY(category_id) REFERENCES Category(category_id));",
                "CREATE TABLE IF NOT EXISTS Supplier (supplier_id INTEGER PRIMARY KEY, company_name TEXT, category_id INTEGER, FOREIGN KEY(category_id) REFERENCES Category(category_id));",
                "CREATE TABLE IF NOT EXISTS Transactions (transaction_id INTEGER PRIMARY KEY, employee_id INTEGER, total_cost REAL, timestamps DATE, FOREIGN KEY(employee_id) REFERENCES Employees(employee_id));",
                "CREATE TABLE IF NOT EXISTS Stocking (order_id INTEGER PRIMARY KEY, supplier_id INTEGER, quantity INTEGER, day DATE, product_id INTEGER, FOREIGN KEY(product_id) REFERENCES Product(product_id));",
                "CREATE TABLE IF NOT EXISTS TransactionItem (transaction_id INTEGER, product_id INTEGER, quantity INTEGER, FOREIGN KEY(transaction_id) REFERENCES Transactions(transaction_id), FOREIGN KEY(product_id) REFERENCES Product(product_id));"
            };
        } catch (SQLException e) {
            System.out.println("Connection error: " + e.getMessage());
        }

        try (Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement()) 
            {
                
            // Turn on Foreign Key enforcement
            stmt.execute("PRAGMA foreign_keys = ON;");
            
            // Execute each statement one by one
            for (String sql : createStatements) 
            {
                stmt.execute(sql);
            }
            System.out.println("Database tables initialized.");
            
        } catch (SQLException e) {
            System.out.println("Initialization error: " + e.getMessage());
        }
    }
}