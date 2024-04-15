package backEnd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfilManipulator {
	// get pic PATH
    public static String path(String nom) {
    String path = null;
    String sql = "SELECT PATH FROM user WHERE NAME = ?";

    try (Connection conn = DatabaseConnector.getConnection();
         PreparedStatement statement = conn.prepareStatement(sql)) {
        statement.setString(1, nom);

        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                path = resultSet.getString("PATH");
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        // Handle the exception or log it as needed
    }

    return path;
}

}
