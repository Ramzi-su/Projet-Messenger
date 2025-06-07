/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
public class LoginDao {

  public static Map<String, Object> login(String username, String password) throws ClassNotFoundException {
    Map<String, Object> user = null;
    Connection conn = SingletonConnection.getInstance();
    try {
      PreparedStatement ps = conn.prepareStatement("SELECT * FROM User WHERE Username = ? AND password = ?");
      ps.setString(1, username);
      ps.setString(2, password);

      ResultSet rs = ps.executeQuery();
      if (rs.next()) {
        user = new HashMap<>();
        user.put("UserID", rs.getInt("UserID"));
        user.put("Username", rs.getString("Username"));
        user.put("Email", rs.getString("Email"));
        user.put("Password", rs.getString("Password")); // Consider security implications
      }
      
      ps.close();
    } catch (SQLException e) {
        System.out.println("user not found");
    }
    return user;
  }
}
