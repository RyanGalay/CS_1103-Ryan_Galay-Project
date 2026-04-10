/*
 * this code is for pre-loading information into the database
 *
 *
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Insert
{
    private static final String URL = "jdbc:sqlite:inventory.db";

    public static void seed() {
        // create catagories
        InfoManager.addCategory(1, "Produce");
        InfoManager.addCategory(2, "Dairy");
        InfoManager.addCategory(3, "Bakery");

        // create products
        InfoManager.addProduct(101, "Honeycrisp Apple", 1.50, 1);
        InfoManager.addProduct(102, "Whole Milk", 3.99, 2);
        InfoManager.addProduct(103, "Sourdough Bread", 5.00, 3);

        System.out.println("Base information created");
    }

}
