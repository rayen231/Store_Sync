package Sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginManipulator {

    public static boolean check(String username, String password) {
        // SQL query to check if the username and password exist in the database
        String sql = "SELECT * "
        		+ "FROM user "
        		+ "WHERE name = ? AND password = ?";
        
        try (Connection conn = UserConnector.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            
            // Set parameters
            statement.setString(1, username);
            statement.setString(2, password);
            
            // Execute the query
            ResultSet resultSet = statement.executeQuery();
            
            // If the result set has any rows, it means the user exists
            return resultSet.next();
            
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to check login credentials.", e);
        }
    }
}
