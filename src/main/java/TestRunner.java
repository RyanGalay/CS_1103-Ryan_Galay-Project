public class TestRunner {
    public static void main(String[] args) {
        System.out.println("Starting the test...");

        // This calls initialization code
        DatabaseManager.initializeDatabase();
        Insert.seed();

        System.out.println("Test complete!");
    }
}
