/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metier;

import Dao.*;
import java.io.Serializable;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Reaction implements Serializable{
     private static final long serialVersionUID = 1L;
    private int ReactionId;
    private int userId;
    private int publicationId;
    private Date date;
    private static ReactionDao reactionDao = new ReactionDao();

    public Reaction(int ReactionId, int userId, int publicationId, Date date) {
        this.ReactionId = ReactionId;
        this.userId = userId;
        this.publicationId = publicationId;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Reaction{" + "ReactionId=" + ReactionId + ", userId=" + userId + ", publicationId=" + publicationId + ", date=" + date + '}';
    }

    
    public int insert() throws SQLException, ClassNotFoundException {
        return reactionDao.insert(userId, publicationId, date);
    }

    public static Reaction getById(int id) throws SQLException, ClassNotFoundException {
        Map<String, Object> reactionMap = reactionDao.getById(id);
        return mapToReaction(reactionMap);
    }

    public static Map<String, Object> getReactionsByPublicationId(int publicationId) throws SQLException, ClassNotFoundException {
        return reactionDao.getReactionsByPublicationId(publicationId);
    }

    private static Reaction mapToReaction(Map<String, Object> map) {
        int ReactionId = (int) map.get("ReactionID");

        int userId = (int) map.get("UserID");
        int publicationId = (int) map.get("PublicationID");
        Date date = (Date) map.get("Date");

        return new Reaction(ReactionId,userId, publicationId, date);
    }

    public void setReactionId(int ReactionId) {
        this.ReactionId = ReactionId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setPublicationId(int publicationId) {
        this.publicationId = publicationId;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public static void setReactionDao(ReactionDao reactionDao) {
        Reaction.reactionDao = reactionDao;
    }
    
    
}
