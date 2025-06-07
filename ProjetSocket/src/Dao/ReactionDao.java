/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ReactionDao {

  // Insert a new reaction
  public int insert(int userId, int publicationId, Date date) throws SQLException, ClassNotFoundException {
    int reactionId = -1;
    Connection conn = SingletonConnection.getInstance();

    String query = "INSERT INTO Reaction (UserID, PublicationID, Date) VALUES (?, ?, ?)";

    try (PreparedStatement ps = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
        ps.setInt(1, userId);
        ps.setInt(2, publicationId);
        ps.setDate(3, new java.sql.Date(date.getTime()));

        int rowsAffected = ps.executeUpdate();

        if (rowsAffected > 0) {
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    reactionId = generatedKeys.getInt(1);
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return reactionId;
}


  // Get reaction by ID
 public Map<String, Object> getById(int id) throws SQLException, ClassNotFoundException {
  Map<String, Object> reaction = null;
  Connection conn = SingletonConnection.getInstance();
  try {
    PreparedStatement ps = conn.prepareStatement("SELECT * FROM Reaction WHERE ReactionID = ?");
    ps.setInt(1, id);

    ResultSet rs = ps.executeQuery();
    if (rs.next()) {
      reaction = new HashMap<>();
      reaction.put("ReactionID", rs.getInt("ReactionID"));
      reaction.put("UserID", rs.getInt("UserID"));
      reaction.put("PublicationID", rs.getInt("PublicationID"));
      reaction.put("Date", rs.getDate("Date"));
    }
    ps.close();
  } catch (SQLException e) {
    e.printStackTrace();
  }
  return reaction;
}
public Map<String, Object> getReactionsByPublicationId(int publicationId) throws SQLException, ClassNotFoundException {
      Map<String, Object> reaction = null;
  Connection conn = SingletonConnection.getInstance();
  try {
    PreparedStatement ps = conn.prepareStatement("SELECT * FROM Reaction WHERE ReactionID = ?");
    ps.setInt(1, publicationId);

    ResultSet rs = ps.executeQuery();
    if (rs.next()) {
      reaction = new HashMap<>();
      reaction.put("ReactionID", rs.getInt("ReactionID"));
      reaction.put("UserID", rs.getInt("UserID"));
      reaction.put("PublicationID", rs.getInt("PublicationID"));
      reaction.put("Date", rs.getDate("Date"));
    }
    ps.close();
  } catch (SQLException e) {
    e.printStackTrace();
  }
  return reaction;
  }}