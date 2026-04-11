import java.sql.*;
import java.util.Scanner;

/*
 * this class acts as the ui and navigator of the program
 *
 * Ryan Galay
 * Apr 10 2026
 */

public class Console {
    private static final String URL = "jdbc:sqlite:inventory.db";
    static boolean cont = true;
    static Scanner scan = new Scanner(System.in);
    static int selection;
    static Employee tempEmp;
    static int tempin;

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

    private static void purchaseOrder()
    {
        boolean loop = true;
        while (loop) {
            System.out.println("Enter the product id, or 0 to complete order");
            tempin = scan.nextInt();

            if(tempin == 0){
                loop = false;
            } else {
                // this should accept the product id and use it to add a product to the order
            }
        }
    }

    private static void Add()
    {
        // this method will allow the user to add things like products, categories, employees, and suppliers
    }

    private static void Delete()
    {
        // this method will allow the user to remove products, categories, employees, suppliers, and transactions
    }

    private static void View()
    {
        // this method will allow the user to view the product table
    }

    private static void Restock()
    {
        // this method should allow the user to select a supplier and purchase items from them, it does not affect anything really though
    }
}
