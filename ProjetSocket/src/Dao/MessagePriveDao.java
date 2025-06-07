package Dao;


import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessagePriveDao implements Serializable {
 private static final long serialVersionUID = 1L;
  // Insert a new message
public int insert(int senderID, Date date, String content, int receiverID) throws SQLException, ClassNotFoundException {
        int messageID = -1;
        Connection conn = SingletonConnection.getInstance();

        String query = "INSERT INTO MessagePrive (SenderID, ReceiverID, Content, Date) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, senderID);
            ps.setInt(2, receiverID);
            ps.setString(3, content);
            ps.setDate(4, date);

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        messageID = generatedKeys.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return messageID;
    }
  // Get message by ID
  public Map<String, Object> getById(int id) throws SQLException, ClassNotFoundException {
    Map<String, Object> message = null;
    Connection conn = SingletonConnection.getInstance();
    try {
      PreparedStatement ps = conn.prepareStatement("SELECT * FROM MessagePrive WHERE MessageID = ?");
      ps.setInt(1, id);

      ResultSet rs = ps.executeQuery();
      if (rs.next()) {
        message = new HashMap<>();
        message.put("MessageID", rs.getInt("MessageID"));
        message.put("SenderID", rs.getInt("SenderID"));
        message.put("ReceiverID", rs.getInt("ReceiverID"));
        message.put("Content", rs.getString("Content"));
        message.put("Date", rs.getDate("Date"));
      }
      ps.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return message;
  }
public static List<Map<String, Object>> getMessagesBetweenUsers(int user1, int user2) throws SQLException, ClassNotFoundException {
    List<Map<String, Object>> messages = new ArrayList<>();
    Connection conn = SingletonConnection.getInstance();
    try {
        PreparedStatement ps = conn.prepareStatement(
                "SELECT * FROM MessagePrive WHERE (SenderID = ? AND ReceiverID = ?) OR (SenderID = ? AND ReceiverID = ?)");
        ps.setInt(1, user1);
        ps.setInt(2, user2);
        ps.setInt(3, user2);
        ps.setInt(4, user1);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Map<String, Object> message = new HashMap<>();
            message.put("MessageID", rs.getInt("MessageID"));
            message.put("SenderID", rs.getInt("SenderID"));
            message.put("ReceiverID", rs.getInt("ReceiverID"));
            message.put("Content", rs.getString("Content"));
            
            // Conversion de la date
            String date1 = rs.getString("Date"); // Suppose rs est votre ResultSet contenant la colonne "Date"
            String dateFormat = "yyyy-MM-dd";
            SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);

            try {
                java.util.Date utilDate = sdf.parse(date1);
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                message.put("Date", sqlDate);
            } catch (ParseException e) {
                e.printStackTrace(); 
                // Gérer l'exception en fonction de vos besoins
            }
            
            messages.add(message);
        }
        ps.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return messages;
}

  // Other methods for interacting with MessagePrive table using Maps
}
