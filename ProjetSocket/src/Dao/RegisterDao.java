/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterDao {
    public static boolean register(String Username, String Email, String Password) throws ClassNotFoundException {
    boolean status = false;
    Connection conn = SingletonConnection.getInstance();
    try {
        // Check if username already exists
        PreparedStatement checkUser = conn.prepareStatement("SELECT * FROM User WHERE Username = ?");
        checkUser.setString(1, Username);
        ResultSet rs = checkUser.executeQuery();

        // If username does not exist, register new user
        if (!rs.next()) {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO User (Username, Email, Password) VALUES (?, ?, ?)");
            ps.setString(1, Username);
            ps.setString(2, Email);
            ps.setString(3, Password);

            status = ps.executeUpdate() > 0;
            ps.close();
        }
        checkUser.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return status;
}
}
