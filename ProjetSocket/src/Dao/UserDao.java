
package Dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import java.util.Map;



public class UserDao {

  // Insert a new user
  public boolean insert(String Username, String Email, String Password) throws SQLException, ClassNotFoundException {
    boolean status = false;
    Connection conn = SingletonConnection.getInstance();
    try {
      PreparedStatement ps = conn.prepareStatement("INSERT INTO User (Username, Email, Password) VALUES (?, ?, ?)");
      ps.setString(1, Username);
      ps.setString(2, Email);
      ps.setString(3, Password);

      status = ps.executeUpdate() > 0;
      ps.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return status;
  }

  // Get user by ID
  public Map<String, Object> getById(int id) throws SQLException, ClassNotFoundException {
    Map<String, Object> user = null;
    Connection conn = SingletonConnection.getInstance();
    try {
      PreparedStatement ps = conn.prepareStatement("SELECT * FROM User WHERE UserID = ?");
      ps.setInt(1, id);

      ResultSet rs = ps.executeQuery();
      if (rs.next()) {
        user = new HashMap<>();
        user.put("UserID", rs.getInt("UserID"));
        user.put("Username", rs.getString("Username"));
        user.put("Email", rs.getString("Email"));
        user.put("Password", rs.getString("Password")); // **Security Risk: Storing plain text password**
      }
      ps.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return user;
  }

  // Other methods for interacting with User table using Maps
}
