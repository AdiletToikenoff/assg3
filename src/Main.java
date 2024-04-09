public class Main {
    public static void main(String[] args) {
        DatabaseConnection dbConnection = DatabaseConnection.getInstance();
        dbConnection.executeQuery("SELECT * FROM users");
        System.out.println("Database connection info: " + dbConnection.getDatabaseConnectionInfo());
    }
}
