/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metier;

import Dao.*;/*LoginDao*/
import Dao.LoginDao;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Map;

public class User implements Serializable{
     private static final long serialVersionUID = 1L;
    private String username;
    private String email;
    private String password;
    private int UserId;
    private static UserDao userDao = new UserDao();

    public User(int UserId,String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.UserId=UserId;
    }

    // Getters and setters are omitted for brevity

    @Override
    public String toString() {
        return "User{" + "username=" + username + ", email=" + email + ", password=" + password + ", UserId=" + UserId + '}';
    }

    

    public boolean register() throws SQLException, ClassNotFoundException {
        return RegisterDao.register(username, email, password);
    }

    public static User login(String username, String password) throws SQLException, ClassNotFoundException {
        Map<String, Object> userMap = LoginDao.login(username, password);
        return mapToUser(userMap);
    }

    public static User getById(int id) throws SQLException, ClassNotFoundException {
        Map<String, Object> userMap = userDao.getById(id);
        return mapToUser(userMap);
    }

    private static User mapToUser(Map<String, Object> map) {
        if(map==null){
            return null;
        }
        else{
        String username = (String) map.get("Username");
        String email = (String) map.get("Email");
        String password = (String) map.get("Password");
        int UserID = (int) map.get("UserID");

        return new User(UserID,username, email, password);
    }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int UserId) {
        this.UserId = UserId;
    }

    public static UserDao getUserDao() {
        return userDao;
    }

    public static void setUserDao(UserDao userDao) {
        User.userDao = userDao;
    }
}

