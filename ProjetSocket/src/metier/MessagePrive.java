/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metier;

/**
 *
 * @author 21652
 */
import Dao.*;
import java.io.Serializable;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MessagePrive implements Serializable{
     private static final long serialVersionUID = 1L;
    private int MessagePriveId;
    private int senderID;
    private int receiverID;
    private String content;
    private Date date;
    private static MessagePriveDao messagePriveDao = new MessagePriveDao();

    public MessagePrive(int MessagePriveId, int senderID, int receiverID, String content, Date date) {
        this.MessagePriveId = MessagePriveId;
        this.senderID = senderID;
        this.receiverID = receiverID;
        this.content = content;
        this.date = date;
    }
  public static List<MessagePrive> getMessagesBetweenUsers(int user1, int user2) throws SQLException, ClassNotFoundException {
        List<Map<String, Object>> messagesBetweenUsers = messagePriveDao.getMessagesBetweenUsers(user1, user2);
        List<MessagePrive> messages = new ArrayList<>();
        for (Map<String, Object> messageBetweenUsers : messagesBetweenUsers) {
            MessagePrive messagePrive = mapToMessagePrive(messageBetweenUsers);
            messages.add(messagePrive);
        }
        return messages;
    }


    private static MessagePrive mapToMessagePrive(Map<String, Object> map) {
        int MessagePriveId = (int) map.get("MessageID");
            int senderID = (int) map.get("SenderID");
        int receiverID = (int) map.get("ReceiverID");
        String content = (String) map.get("Content");
        Date date = (Date) map.get("Date");

        return new MessagePrive(MessagePriveId,senderID, receiverID, content, date);
    }
    public MessagePrive() {
    }

    public int getMessagePriveId() {
        return MessagePriveId;
    }

    @Override
    public String toString() {
        return "MessagePrive{" + "MessagePriveId=" + MessagePriveId + ", senderID=" + senderID + ", receiverID=" + receiverID + ", content=" + content + ", date=" + date + '}';
    }





    public static MessagePrive getById(int id) throws SQLException, ClassNotFoundException {
        Map<String, Object> messageMap = messagePriveDao.getById(id);
        return mapToMessagePrive(messageMap);
    }

  
    public int insert() throws SQLException, ClassNotFoundException {
        return messagePriveDao.insert(senderID, date, content, receiverID);
    }

    public void setMessagePriveId(int MessagePriveId) {
        this.MessagePriveId = MessagePriveId;
    }

    public int getSenderID() {
        return senderID;
    }

    public void setSenderID(int senderID) {
        this.senderID = senderID;
    }

    public int getReceiverID() {
        return receiverID;
    }

    public void setReceiverID(int receiverID) {
        this.receiverID = receiverID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public static MessagePriveDao getMessagePriveDao() {
        return messagePriveDao;
    }

    public static void setMessagePriveDao(MessagePriveDao messagePriveDao) {
        MessagePrive.messagePriveDao = messagePriveDao;
    }

}

