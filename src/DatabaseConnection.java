import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
    private static final String CONFIG_FILE = "config.properties";
    private static Connection connection;

    private static DatabaseConnection instance;

    private DatabaseConnection() {
        loadConfiguration();
    }

    public static synchronized DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public void executeQuery(String query) {
        try {
            // код, который может вызвать SQLException
            connection.prepareStatement("SELECT * FROM table");
        } catch (SQLException e) {
            // обработка исключения
            e.printStackTrace();
        }

    }

    public String getDatabaseConnectionInfo() {
        return "Connection Info"; // Верните информацию о соединении
    }

    private void loadConfiguration() {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader(CONFIG_FILE));
            String hostname = properties.getProperty("hostname");
            String databaseName = properties.getProperty("databaseName");
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");

            String url = "jdbc:mysql://" + hostname + "/" + databaseName;
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Успешное подключение к базе данных");
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}