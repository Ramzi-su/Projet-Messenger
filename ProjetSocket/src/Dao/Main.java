package Dao;

import java.util.Map;
import java.sql.SQLException;
import java.util.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import metier.MessagePrive;
import metier.User;
public class Main {

       public static void main(String[] args) {
        int user1 = 3; // replace with actual user ID
        int user2 = 4; // replace with actual user ID

        try {
            List<MessagePrive> messages = MessagePrive.getMessagesBetweenUsers(user1, user2);

            for (MessagePrive message : messages) {
                System.out.println("Message : " + message);
           
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
