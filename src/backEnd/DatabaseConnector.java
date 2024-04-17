package backEnd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {

 // JDBC URL, username, and password of MySQL server
    private static final String URL = "jdbc:mysql://localhost:3306/Project";
    private static Connection connection = null;

    // Private constructor to prevent instantiation from outside
    private DatabaseConnector() {}

    public static synchronized Connection getConnection() {
    	//there's a chance that multiple threads could concurrently access the getConnection() method thats why synchronized made it Thread Safety
        try {
            // Check if connection is closed or invalid, then create a new one
            if (connection == null || connection.isClosed()) {
                createConnection();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to check connection status.", e);
        }
        return connection;
    }

    private static synchronized void createConnection() {
        try {
            connection = DriverManager.getConnection(URL, "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to connect to database.", e);
        }
    }
    public static void testConnection() {
        try (Connection conn = getConnection()) {
            if (conn != null) {
                System.out.println("Connected to the database successfully.");
            } else {
                System.out.println("Failed to connect to the database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to connect to the database.");
        }
    }
}