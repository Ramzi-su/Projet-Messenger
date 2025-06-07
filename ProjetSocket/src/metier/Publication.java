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

public class Publication implements Serializable{
     private static final long serialVersionUID = 1L;
    private int PublicationId;
    
    private int userId;
    private String content;
    private Date date;
    private static PublicationDao publicationDao = new PublicationDao();

    public Publication(int PublicationId, int userId, String content, Date date) {
        this.PublicationId = PublicationId;
        this.userId = userId;
        this.content = content;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Publication{" + "PublicationId=" + PublicationId + ", userId=" + userId + ", content=" + content + ", date=" + date + '}';
    }



    public void setPublicationId(int PublicationId) {
        this.PublicationId = PublicationId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    // Getters and setters are omitted for brevity
    public static void setPublicationDao(PublicationDao publicationDao) {
        Publication.publicationDao = publicationDao;
    }

    public int insert() throws SQLException, ClassNotFoundException {
        return publicationDao.insertPublication(userId, content, date);
    }

    public static Publication getById(int id) throws SQLException, ClassNotFoundException {
        Map<String, Object> publicationMap = publicationDao.getById(id);
        return mapToPublication(publicationMap);
    }

    public static List<Publication> getPublicationsByUser(int userId) throws SQLException, ClassNotFoundException {
        List<Map<String, Object>> publicationsMap = publicationDao.getPublicationsByUser(userId);
        List<Publication> publications = new ArrayList<>();
        for (Map<String, Object> publicationMap : publicationsMap) {
            Publication publication = mapToPublication(publicationMap);
            publications.add(publication);
        }
        return publications;
    }
    public static List<Publication> getAllPublications() throws SQLException, ClassNotFoundException {
        List<Map<String, Object>> publicationsMap = publicationDao.getAllPublications();
        List<Publication> publications = new ArrayList<>();
        for (Map<String, Object> publicationMap : publicationsMap) {
            Publication publication = mapToPublication(publicationMap);
            publications.add(publication);
        }
        return publications;
    }
    public List<Commentaire> getCommentaires() throws SQLException, ClassNotFoundException {
        return Commentaire.getCommentairesByPublicationId(PublicationId);
    }

    public Map<String, Object> getReactions() throws SQLException, ClassNotFoundException {
        return Reaction.getReactionsByPublicationId(PublicationId);
    }

    private static Publication mapToPublication(Map<String, Object> map) {
        int PublicationID = (int) map.get("PublicationID");

        int userId = (int) map.get("UserID");
        String content = (String) map.get("Content");
        Date date = (Date) map.get("Date");

        return new Publication(PublicationID,userId, content, date);
    }
}
