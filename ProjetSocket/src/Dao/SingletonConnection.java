/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class SingletonConnection {
    Properties config=new Properties();
private static String user;
private static String password;
private static String url;
private static Connection connect;
private SingletonConnection(){
try {
	
	config.load(new FileInputStream("config.properties"));
	url=config.getProperty("jdbc.url");
	user=config.getProperty("jdbc.user");
	password=config.getProperty("jdbc.password");
	connect = DriverManager.getConnection(url, user, password);
} 
catch (SQLException e)
{ e.printStackTrace();; 
}
catch(IOException e)
{
	e.printStackTrace();
}
}
public static Connection getInstance() throws ClassNotFoundException{
if(connect == null){
	
new SingletonConnection();
}
return connect;
}





}
