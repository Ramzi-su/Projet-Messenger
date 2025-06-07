/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommentaireDao {

  // Insert a new comment
public int insert(int userId, int publicationId, String content, Date date) throws SQLException, ClassNotFoundException {
    int commentId = -1;
    Connection conn = SingletonConnection.getInstance();

    String query = "INSERT INTO Commentaire (UserID, PublicationID, Content, Date) VALUES (?, ?, ?, ?)";

    try (PreparedStatement ps = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
        ps.setInt(1, userId);
        ps.setInt(2, publicationId);
        ps.setString(3, content);
        ps.setDate(4, new java.sql.Date(date.getTime()));

        int rowsAffected = ps.executeUpdate();

        if (rowsAffected > 0) {
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    commentId = generatedKeys.getInt(1);
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return commentId;
}


  // Get comment by ID
  public Map<String, Object> getById(int id) throws SQLException, ClassNotFoundException {
    Map<String, Object> commentaire = null;
    Connection conn = SingletonConnection.getInstance();
    try {
      PreparedStatement ps = conn.prepareStatement("SELECT * FROM Commentaire WHERE CommentaireID = ?");
      ps.setInt(1, id);

      ResultSet rs = ps.executeQuery();
      if (rs.next()) {
        commentaire = new HashMap<>();
        commentaire.put("CommentaireID", rs.getInt("CommentaireID"));
        commentaire.put("UserID", rs.getInt("UserID"));
        commentaire.put("PublicationID", rs.getInt("PublicationID"));
        commentaire.put("Content", rs.getString("Content"));
        commentaire.put("Date", rs.getDate("Date"));
      }
      ps.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return commentaire;
  }

public List<Map<String, Object>> getCommentairesByPublicationId(int publicationId) throws SQLException, ClassNotFoundException {
    List<Map<String, Object>> commentaires = new ArrayList<>();
    Connection conn = SingletonConnection.getInstance();

    // Query for commentaires
    PreparedStatement psCommentaires = conn.prepareStatement("SELECT * FROM Commentaire WHERE PublicationID = ?");
    psCommentaires.setInt(1, publicationId);
    ResultSet rsCommentaires = psCommentaires.executeQuery();

    while (rsCommentaires.next()) {
      Map<String, Object> commentaire = new HashMap<>();
      commentaire.put("senderId", rsCommentaires.getInt("UserID"));
      commentaire.put("Content", rsCommentaires.getString("Content"));
      commentaires.add(commentaire);
    }

    return commentaires;
  }}
