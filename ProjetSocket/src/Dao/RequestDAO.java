package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RequestDAO {
    
    public int insert(int senderId, int receiverId) throws SQLException, ClassNotFoundException {
    int requestId = -1;
    Connection conn = SingletonConnection.getInstance();

    String query = "INSERT INTO request (id_sender, idrec) VALUES (?, ?)";

    try (PreparedStatement ps = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
        ps.setInt(1, senderId);
        ps.setInt(2, receiverId);

        int rowsAffected = ps.executeUpdate();

        if (rowsAffected > 0) {
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    requestId = generatedKeys.getInt(1); // Récupérer l'identifiant de la demande généré
                }
            }
        }
    } catch (SQLException e) {
        
        e.printStackTrace();
    }

    return requestId;
}
}
