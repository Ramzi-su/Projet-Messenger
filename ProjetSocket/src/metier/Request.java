package metier;

import Dao.RequestDAO;
import java.sql.SQLException;

public class Request {
    private int senderId;
    private int receiverId;

    public Request(int senderId, int receiverId) {
        this.senderId = senderId;
        this.receiverId = receiverId;
    }

    public void sendFriendRequest() throws SQLException, ClassNotFoundException {
        // Créer une instance de RequestDAO
        RequestDAO requestDAO = new RequestDAO();
        
        // Appeler la méthode insert sur cette instance
        requestDAO.insert(senderId, receiverId);
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }
}
