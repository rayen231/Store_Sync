package backEnd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import frontEnd.SalesRecord;

public class SalesManipulator {

    public static List<SalesRecord> upload(Date fromDate, Date toDate) {
        List<SalesRecord> salesRecords = new ArrayList<>();
        
        // SQL query to select all rows from the "Sales" table within the specified date range
        String sql = "SELECT * FROM Sales WHERE DATE BETWEEN ? AND ?";
        
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            
            // Set parameters
            statement.setDate(1, new java.sql.Date(fromDate.getTime()));
            statement.setDate(2, new java.sql.Date(toDate.getTime()));
            
            // Execute the query
            ResultSet resultSet = statement.executeQuery();
            
            // Iterate over the result set and create SalesRecord objects
            while (resultSet.next()) {
                Date salesDate = resultSet.getDate("DATE");
                double expenses = resultSet.getDouble("EXPENSES");
                double income = resultSet.getDouble("INCOME");
                double profit = resultSet.getDouble("PROFIT");
                
                // Create a SalesRecord object and add it to the list
                SalesRecord record = new SalesRecord(salesDate, expenses, income, profit);
                salesRecords.add(record);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to fetch sales records from the database.", e);
        }
        
        return salesRecords;
    }
    public static double avgIncomeFromDatabase() {
        String sql = "SELECT AVG(INCOME) AS avgIncome FROM Sales";
        
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            
            if (resultSet.next()) {
                return resultSet.getDouble("avgIncome");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to calculate average income from the database.", e);
        }
        
        return 0.0; // Return 0.0 if no result is obtained
    }
}
