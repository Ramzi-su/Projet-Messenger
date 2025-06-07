/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PublicationDao {

  // Insert a new publication
public int insertPublication(int userID, String content, Date date) throws SQLException, ClassNotFoundException {
    int publicationID = -1;
    Connection conn = SingletonConnection.getInstance();

    String query = "INSERT INTO Publication (UserID, Content, Date) VALUES (?, ?, ?)";

    try (PreparedStatement ps = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
        ps.setInt(1, userID);
        ps.setString(2, content);
        ps.setDate(3, new java.sql.Date(date.getTime()));

        int rowsAffected = ps.executeUpdate();

        if (rowsAffected > 0) {
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    publicationID = generatedKeys.getInt(1);
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return publicationID;
}

   public Map<String, Object> getById(int id) throws SQLException, ClassNotFoundException {
    Map<String, Object> publication = null;
    Connection conn = SingletonConnection.getInstance();
    try {
      PreparedStatement ps = conn.prepareStatement("SELECT * FROM Publication WHERE PublicationID = ?");
      ps.setInt(1, id);

      ResultSet rs = ps.executeQuery();
      if (rs.next()) {
        publication = new HashMap<>();
        publication.put("PublicationID", rs.getInt("PublicationID"));
        publication.put("UserID", rs.getInt("UserID"));
        publication.put("Content", rs.getString("Content"));
        publication.put("Date", rs.getDate("Date"));
      }
      ps.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return publication;
  }
public List<Map<String, Object>> getPublicationsByUser(int userId) throws SQLException, ClassNotFoundException {
    List<Map<String, Object>> publications = new ArrayList<>();
    Connection conn = SingletonConnection.getInstance();

    // Query for publications
    PreparedStatement psPublications = conn.prepareStatement("SELECT * FROM Publication WHERE UserID = ?");
    psPublications.setInt(1, userId);
    ResultSet rsPublications = psPublications.executeQuery();

    while (rsPublications.next()) {
        Map<String, Object> publication = new HashMap<>();
        publication.put("PublicationID", rsPublications.getInt("PublicationID"));
        publication.put("UserID", rsPublications.getInt("UserID"));
        publication.put("Content", rsPublications.getString("Content"));
        publication.put("Date", rsPublications.getDate("Date"));

      

       
        
       

        publications.add(publication);
    }

    return publications;
}

  // Get all publications by all users along with their reactions and commentaires
public List<Map<String, Object>> getAllPublications() throws SQLException, ClassNotFoundException {
    List<Map<String, Object>> publications = new ArrayList<>();
    Connection conn = SingletonConnection.getInstance();

    // Query for publications
    PreparedStatement psPublications = conn.prepareStatement("SELECT * FROM Publication");
    ResultSet rsPublications = psPublications.executeQuery();

    while (rsPublications.next()) {
        Map<String, Object> publication = new HashMap<>();
        publication.put("PublicationID", rsPublications.getInt("PublicationID"));
        publication.put("UserID", rsPublications.getInt("UserID"));
        publication.put("Content", rsPublications.getString("Content"));
        publication.put("Date", rsPublications.getDate("Date"));

        

        

        publications.add(publication);
    }

    return publications;
  }}
