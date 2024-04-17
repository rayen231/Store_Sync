package backEnd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.jfree.data.category.DefaultCategoryDataset;

public class HomeManipulator {
	public static double getTotalIncome() {
        double totalIncome = 0.0;

        // SQL query to select sum of all income
        String sql = "SELECT SUM(INCOME) AS TOTAL_INCOME FROM Sales";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            // Retrieve the total income
            if (resultSet.next()) {
                totalIncome = resultSet.getDouble("TOTAL_INCOME");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to calculate total income from the database.", e);
        }

        return totalIncome;
    }
	
	public static DefaultCategoryDataset generateDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // SQL query to select all dates and incomes from the Sales table
        String sql = "SELECT DATE, INCOME FROM Sales";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            // Iterate over the result set and add data to the dataset
            while (resultSet.next()) {
                Date date = resultSet.getDate("DATE");
                double income = resultSet.getDouble("INCOME");

                // Add data to the dataset
                dataset.addValue(income, "Income", date.toString());
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to generate chart data from the database.", e);
        }

        return dataset;
    }

}
