import java.sql.*;
import java.util.Scanner;

/*
 * this class acts as the ui and navigator of the program
 *
 * Ryan Galay
 * Apr 10 2026
 */

public class Console
{
    private static final String URL = "jdbc:sqlite:inventory.db";
    static boolean cont = true;
    static Scanner scan = new Scanner(System.in);
    static int selection;

    public static void main(String[] args)
    {
        System.out.println("Please enter your employee id: (1 for all functions)");
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

                } else {
                    System.out.println("No record found with ID: " + check);
                }
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }

        while (cont)
        {
            System.out.println("Employee, choose function:");
            System.out.println();
            System.out.println("1.)");
            System.out.println("2.)");
            System.out.println("3.)");
            System.out.println("4.) close program");
            selection = scan.nextInt();

            if (selection == 4){
                cont = false;
            }

            while (cont)
            {
                System.out.println("Employee, choose function:");
                System.out.println();
                System.out.println("1.)");
                System.out.println("2.)");
                System.out.println("3.)");
                System.out.println("4.) close program");
                selection = scan.nextInt();

                if (selection == 4){
                    cont = false;
                }
        }
        // close scanner
        scan.close();
    }
}
