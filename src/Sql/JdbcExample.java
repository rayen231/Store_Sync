package Sql;
import java.sql.*;

public class JdbcExample {
    public static void main(String args[]) {
        Connection con = null;
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            
            // Establish connection to the database
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project");
            
            // Check if connection is successful
            if (!con.isClosed())
                System.out.println("Successfully connected to MySQL server...");
        } catch (Exception e) {
            // Handle any exceptions that occur
            System.err.println("Exception: " + e.getMessage());
        } finally {
            try {
                // Close the connection in the finally block to ensure it gets closed even if an exception occurs
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                // Handle SQL exception if encountered while closing the connection
                System.err.println("SQL Exception: " + e.getMessage());
            }
        }
    }
}
