package backEnd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EditUserManipulator {
	 public void edit(String user,String desc,String path)
	    {
	    	// SQL query to update the user table
	        String sql = "UPDATE user SET DESCRIPTION = ?, PATH = ? WHERE NAME = ?";

	        try (Connection conn = DatabaseConnector.getConnection();
	             PreparedStatement statement = conn.prepareStatement(sql)) {

	            // Set parameters
	            statement.setString(1, desc);
	            statement.setString(2, path);
	            statement.setString(3, user);

	            // Execute the update
	            int rowsUpdated = statement.executeUpdate();

	            // Check if the update was successful
	            if (rowsUpdated > 0) {
	                System.out.println("User information updated successfully.");
	            } else {
	                System.out.println("No user found with the specified ID.");
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	            // Handle any potential errors
	        }
	    	
	    }

}
