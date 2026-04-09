
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
    private static final String URL = "jdbc:sqlite:grocery_store.db";

    public static void initializeDatabase() {
        // List all 7 of your table creation scripts here
        String[] createStatements = {
            "CREATE TABLE IF NOT EXISTS Employees (employee_id INTEGER PRIMARY KEY, full_name TEXT, title TEXT, salary REAL, permissions TEXT);",
            "CREATE TABLE IF NOT EXISTS Product (product_id INTEGER PRIMARY KEY, name TEXT, price REAL, category_id INTEGER, FOREIGN KEY(category_id) REFERENCES Categories(category_id));",
            "CREATE TABLE IF NOT EXISTS Supplier (supplier_id INTEGER PRIMARY KEY, company_name TEXT, category_id INTEGER, FOREIGN KEY(category_id) REFERENCES Categories(category_id));",
            "create table if not exists Category (category_id INTEGER primary key, category_name TEXT);",
            "create table if not exists Stocking (order_id INTEGER PRIMARY KEY, supplier_id INTEGER, quantity INTEGER, day DATE, product_id INTEGER, FOREIGN KEY(product_id) REFERENCES Product(product_id));",
            "CREATE TABLE IF NOT EXISTS Transactions (transaction_id INTEGER PRIMARY KEY, employee_id INTEGER, Foreign key(employee_id) references Employees(employee_id), total_cost REAL, timestamps DATE);",
            "create table if not exists TransactionItem (transaction_id INTEGER, foreign key(transaction_id) references Transactions(transaction_id), product_id INTEGER, FOREIGN KEY(product_id) REFERENCES Product(product_id), quantity INTEGER);",
            // ... and so on for all 7 tables
        };

        try (Connection conn = DriverManager.getConnection(URL);
            Statement stmt = conn.createStatement()) 
            {
            
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