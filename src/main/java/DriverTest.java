
import java.sql.DriverManager;

public class DriverTest {
    public static void test() {
        try {
            Class.forName("org.sqlite.JDBC");
            System.out.println("Success! BlueJ sees the SQLite Driver.");
        } catch (Exception e) {
            System.out.println("Failure: BlueJ still can't see the JAR content.");
            e.printStackTrace();
        }
    }
}