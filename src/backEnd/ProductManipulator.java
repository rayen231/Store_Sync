package backEnd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductManipulator {
    public void addProduct(String name, String type, String place, String quantity) {
        String sql = "INSERT INTO Products (Name, Quantity, Type, Place) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, quantity);
            pstmt.setString(3, type);
            pstmt.setString(4, place);

            pstmt.executeUpdate();
            System.out.println("Product added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to add product.");
        }
    }

    public List<String[]> searchProduct(int id) {
        List<String[]> productList = new ArrayList<>();
        String sql = "SELECT ID, Name, Quantity, Type, Place FROM Products WHERE id = ?";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String productId = rs.getString("ID");
                String name = rs.getString("Name");
                String quantity = rs.getString("Quantity");
                String type = rs.getString("Type");
                String place = rs.getString("Place");

                // Add the retrieved values to the list
                productList.add(new String[]{productId, name, quantity, type, place});
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Handle SQL exception
        }

        return productList;
    }

    public List<String[]> loadDataFromDatabase() {
        List<String[]> productList = new ArrayList<>();
        String sql = "SELECT ID, Name, Quantity, Type, Place FROM Products";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            // Clear existing data
            while (rs.next()) {
                String productId = rs.getString("ID");
                String name = rs.getString("Name");
                String quantity = rs.getString("Quantity");
                String type = rs.getString("Type");
                String place = rs.getString("Place");
                productList.add(new String[]{productId, name, quantity, type, place});
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Handle SQL exception
        }
        return productList;
    }

    public void deleteProductById(int id) {
        String sql = "DELETE FROM Products WHERE id = ?";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

            System.out.println("Product with ID " + id + " deleted successfully.");
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Failed to delete product with ID " + id);
        }
    }

    public boolean updateProductById(int id, String name, String type, String place, int quantity) {
        String sql = "UPDATE Products SET Name = ?, Type = ?, Place = ?, Quantity = ? WHERE id = ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, type);
            pstmt.setString(3, place);
            pstmt.setInt(4, quantity);
            pstmt.setInt(5, id);

            int rowsUpdated = pstmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
