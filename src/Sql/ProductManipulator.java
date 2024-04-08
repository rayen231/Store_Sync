package Sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductManipulator {
	public static void addProduct(int id, String name,String type, String place, int quantity) {
        String sql = "INSERT INTO Products (ID, Name, Type, Place, Quantity) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setString(3, type);
            pstmt.setString(4, place);
            pstmt.setInt(5, quantity);
            pstmt.executeUpdate();
            System.out.println("Product added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to add product.");
        }
    }
}

