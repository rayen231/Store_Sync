package backEnd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

public class MakeBillManipulator {
	
	
	// printer function
    public static void printPDF(String filename) {
  	  try {
  	    // Open the PDF with the default PDF viewer
  	    Runtime.getRuntime().exec("explorer.exe " + filename);
  	    // This might prompt a print dialog within the viewer.
  	  } catch (Exception e) {
  	    e.printStackTrace();
  	  }
  	}
    
    
  //add bill in DB
    public void add(String cin,String name,Map<String, Integer> items)
    {
    	// Convert the map to a string format
        StringBuilder itemsStr = new StringBuilder();
        for (Map.Entry<String, Integer> entry : items.entrySet()) {
            itemsStr.append(entry.getKey()).append(":").append(entry.getValue()).append(",");
        }
        String itemsString = itemsStr.toString();

        // SQL statement to insert data into the Bills table
        String sql = "INSERT INTO Bills (CIN, NAME, ITEMS) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, cin);
            statement.setString(2, name);
            statement.setString(3, itemsString);

            // Execute the insert statement
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception or log it as needed
        }
    }

}
