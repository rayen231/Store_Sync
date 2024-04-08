package Sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    // JDBC URL, username, and password of MySQL server
    private static final String URL = "jdbc:mysql://localhost:3306/Project";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL,"root","");
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