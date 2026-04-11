import java.sql.*;
import java.util.Scanner;

/*
 * this class acts as the ui and navigator of the program
 *
 * Ryan Galay
 * Apr 10 2026
 *
 * google gemini was used in this project to clean up code and speed up repetitive tasks
 *
 * Use of intellij is recommended for functionality of database
 * I could not make bluej work personally
 */

public class Console {
    // initialize variables
    private static final String URL = "jdbc:sqlite:inventory.db";
    static boolean cont = true;
    static Scanner scan = new Scanner(System.in);
    static int selection;
    static Employee tempEmp;

    // main method used to operate system
    public static void main(String[] args) {
        System.out.println("Please enter your employee id: (1 for Manager, 2 for Employee)");
        int check = scan.nextInt();
        String sqlLogin = ("SELECT * FROM Employees WHERE employee_id = ?");

        try (
                // Establish connection
                Connection conn = DriverManager.getConnection(URL);
                // Create prepared statement
                PreparedStatement pstmt = conn.prepareStatement(sqlLogin)
        ) {
            // Set the primary key value in the query
            pstmt.setInt(1, check);

            // Execute query
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    // Retrieve values by column name

                    int employee_id = rs.getInt("employee_id");
                    String full_name = rs.getString("full_name");
                    String title = rs.getString("title");
                    double salary = rs.getDouble("salary");
                    String permissions = rs.getString("permissions");

                    tempEmp = new Employee(employee_id, full_name, title, salary, permissions);

                } else {
                    System.out.println("No record found with ID: " + check);
                }
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }

        if ((tempEmp.permissions).equals("Employee"))
        {
            while (cont) {
                System.out.println("Employee, choose function:");
                System.out.println();
                System.out.println("1.) Purchase Order");
                System.out.println("2.) View products");
                System.out.println("4.) close program");
                selection = scan.nextInt();

                if (selection == 3)
                {
                    cont = false;
                } else if (selection == 1)
                {
                    purchaseOrder();
                } else if (selection == 2)
                {
                    View();
                }
            }
        }

        if ((tempEmp.permissions).equals("Manager")){
            while (cont) {
                System.out.println("Manager, choose function:");
                System.out.println();
                System.out.println("1.) Purchase Order");
                System.out.println("2.) Add");
                System.out.println("3.) Delete");
                System.out.println("4.) View products");
                System.out.println("5.) Restock");
                System.out.println("6.) close program");
                selection = scan.nextInt();

                if (selection == 6)
                {
                    cont = false;
                } else if (selection == 1)
                {
                    purchaseOrder();
                } else if (selection == 2)
                {
                    Add();
                } else if (selection == 3)
                {
                    Delete();
                } else if (selection == 4)
                {
                    View();
                } else if (selection == 5)
                {
                    Restock();
                }
            }
        }

        // close scanner
        scan.close();
    }

    // allows user to process a purchase order
    private static void purchaseOrder() {
        System.out.println("\n--- New Sale Transaction ---");

        try (Connection conn = DriverManager.getConnection(URL)) {
            conn.setAutoCommit(false);

            // Create initial transaction record
            String sqlMain = "INSERT INTO Transactions (employee_id, total_cost, timestamps) VALUES (?, ?, CURRENT_DATE)";
            int transactionId = -1;
            double runningTotal = 0.0;

            // run sql statement
            try (PreparedStatement pstmtMain = conn.prepareStatement(sqlMain)) {
                pstmtMain.setInt(1, tempEmp.employee_id);
                pstmtMain.setDouble(2, 0.0);
                pstmtMain.executeUpdate();

                try (Statement stmt = conn.createStatement();
                     ResultSet rs = stmt.executeQuery("SELECT last_insert_rowid()")) {
                    if (rs.next()) {
                        transactionId = rs.getInt(1);
                    }
                }
            }

            // add products
            while (true) {
                System.out.print("Enter product id (or 0 to finish): ");
                int pId = scan.nextInt();
                if (pId == 0) break;

                System.out.print("Enter quantity: ");
                int qty = scan.nextInt();

                // Get price from Product table to calculate cost
                double price = 0;
                String sqlPrice = "SELECT price FROM Product WHERE product_id = ?";
                try (PreparedStatement pstmtPrice = conn.prepareStatement(sqlPrice)) {
                    pstmtPrice.setInt(1, pId);
                    try (ResultSet rs = pstmtPrice.executeQuery()) {
                        if (rs.next()) {
                            price = rs.getDouble("price");
                        } else {
                            System.out.println("Product ID not found. Skipping item.");
                            continue;
                        }
                    }
                }

                runningTotal += (price * qty);

                // Insert into TransactionItem
                String sqlItem = "INSERT INTO TransactionItem (transaction_id, product_id, quantity) VALUES (?, ?, ?)";
                try (PreparedStatement pstmtItem = conn.prepareStatement(sqlItem)) {
                    pstmtItem.setInt(1, transactionId);
                    pstmtItem.setInt(2, pId);
                    pstmtItem.setInt(3, qty);
                    pstmtItem.executeUpdate();
                    System.out.println("Added: " + qty + " units @ $" + price + " each.");
                }
            }

            // Update the final total_cost in the Transactions table
            String sqlUpdateTotal = "UPDATE Transactions SET total_cost = ? WHERE transaction_id = ?";
            try (PreparedStatement pstmtUpdate = conn.prepareStatement(sqlUpdateTotal)) {
                pstmtUpdate.setDouble(1, runningTotal);
                pstmtUpdate.setInt(2, transactionId);
                pstmtUpdate.executeUpdate();
            }

            conn.commit();
            System.out.printf("Transaction finalized. Total Cost: $%.2f%n", runningTotal);

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }

    // allows user to create a product, category, or employee
    private static void Add() {
        System.out.println("\nAdd: 1.) Product 2.) Category 3.) Employee");
        int choice = scan.nextInt();
        scan.nextLine();

        try (Connection conn = DriverManager.getConnection(URL)) {
            switch (choice) {
                // create new product
                case 1:
                    System.out.print("Product Name: ");
                    String name = scan.nextLine();
                    System.out.print("Price: ");
                    double price = scan.nextDouble();
                    System.out.print("Category ID (1:Produce, 2:Dairy, 3:Bakery): ");
                    int catId = scan.nextInt();

                    String sqlProd = "INSERT INTO Product (name, price, category_id) VALUES (?, ?, ?)";
                    try (PreparedStatement pstmt = conn.prepareStatement(sqlProd)) {
                        pstmt.setString(1, name);
                        pstmt.setDouble(2, price);
                        pstmt.setInt(3, catId);
                        pstmt.executeUpdate();
                        System.out.println("Product added successfully.");
                    }
                    break;

                // create new category
                case 2:
                    System.out.print("New Category Name: ");
                    String catName = scan.nextLine();
                    String sqlCat = "INSERT INTO Category (category_name) VALUES (?)";
                    try (PreparedStatement pstmt = conn.prepareStatement(sqlCat)) {
                        pstmt.setString(1, catName);
                        pstmt.executeUpdate();
                        System.out.println("Category added.");
                    }
                    break;

                // create new employee
                case 3:
                    System.out.print("Full Name: ");
                    String fname = scan.nextLine();
                    System.out.print("Title: ");
                    String title = scan.nextLine();
                    System.out.print("Salary: ");
                    double sal = scan.nextDouble();
                    scan.nextLine();
                    System.out.print("Permissions (Manager/Employee): ");
                    String perm = scan.nextLine();

                    String sqlEmp = "INSERT INTO Employees (full_name, title, salary, permissions) VALUES (?, ?, ?, ?)";
                    try (PreparedStatement pstmt = conn.prepareStatement(sqlEmp)) {
                        pstmt.setString(1, fname);
                        pstmt.setString(2, title);
                        pstmt.setDouble(3, sal);
                        pstmt.setString(4, perm);
                        pstmt.executeUpdate();
                        System.out.println("Employee added.");
                    }
                    break;
            }
        } catch (SQLException e) { System.out.println("Add Error: " + e.getMessage()); }
    }

    // allows user to delete a product
    private static void Delete() {
        System.out.print("\nEnter Product ID to delete: ");
        int id = scan.nextInt();

        // delete selected product
        String sql = "DELETE FROM Product WHERE product_id = ?";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int rows = pstmt.executeUpdate();
            if (rows > 0) System.out.println("Product deleted.");
            else System.out.println("ID not found.");
        } catch (SQLException e) { System.out.println("Delete Error: " + e.getMessage()); }
    }

    // allows user to view products
    private static void View() {
        // Joining Product and Category for a better UI
        String sql = "SELECT p.product_id, p.name, p.price, c.category_name " +
                "FROM Product p JOIN Category c ON p.category_id = c.category_id";

        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\nID | Name | Price | Category");
            System.out.println("---------------------------------");
            while (rs.next()) {
                System.out.printf("%d | %-15s | $%.2f | %s\n",
                        rs.getInt("product_id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getString("category_name"));
            }
        } catch (SQLException e) { System.out.println("View Error: " + e.getMessage()); }
    }

    // method to restock a product (does nothing other than save to stocking table
    private static void Restock() {
        System.out.println("\n--- Supplier Restock ---");
        System.out.print("Enter Supplier ID: ");
        int sId = scan.nextInt();
        System.out.print("Enter Product ID: ");
        int pId = scan.nextInt();
        System.out.print("Quantity: ");
        int qty = scan.nextInt();

        // Mapping for Stocking table: supplier_id, quantity, day, product_id
        String sql = "INSERT INTO Stocking (supplier_id, quantity, day, product_id) VALUES (?, ?, CURRENT_DATE, ?)";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, sId);
            pstmt.setInt(2, qty);
            pstmt.setInt(3, pId);
            pstmt.executeUpdate();
            System.out.println("Restock order logged in Stocking table.");
        } catch (SQLException e) { System.out.println("Restock Error: " + e.getMessage()); }
    }
}