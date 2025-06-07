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

public class Commentaire implements Serializable{
     private static final long serialVersionUID = 1L;
    private int CommentaireId;
    
    private int userId;
    private int publicationId;
    private String content;
    private Date date;
    private static CommentaireDao commentaireDao = new CommentaireDao();


    // Getters and setters are omitted for brevity

   
    public Commentaire(int CommentaireId, int userId, int publicationId, String content, Date date) {
        this.CommentaireId = CommentaireId;
        this.userId = userId;
        this.publicationId = publicationId;
        this.content = content;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "CommentaireId=" + CommentaireId + ", userId=" + userId + ", publicationId=" + publicationId + ", content=" + content + ", date=" + date + '}';
    }

    public int insert() throws SQLException, ClassNotFoundException {
        return commentaireDao.insert(userId, publicationId, content, date);
    }

    public static Commentaire getById(int id) throws SQLException, ClassNotFoundException {
        Map<String, Object> commentaireMap = commentaireDao.getById(id);
        return mapToCommentaire(commentaireMap);
    }

    public static List<Commentaire> getCommentairesByPublicationId(int publicationId) throws SQLException, ClassNotFoundException {
        List<Map<String, Object>> commentairesMap = commentaireDao.getCommentairesByPublicationId(publicationId);
        List<Commentaire> commentaires = new ArrayList<>();
        for (Map<String, Object> commentaireMap : commentairesMap) {
            Commentaire commentaire = mapToCommentaire(commentaireMap);
            commentaires.add(commentaire);
        }
        return commentaires;
    }

    private static Commentaire mapToCommentaire(Map<String, Object> map) {
        int CommentaireId = (int) map.get("CommentaireID");

        int userId = (int) map.get("UserID");
        int publicationId = (int) map.get("PublicationID");
        String content = (String) map.get("Content");
        Date date = (Date) map.get("Date");

        return new Commentaire(CommentaireId,userId, publicationId, content, date);
    }

    public void setCommentaireId(int CommentaireId) {
        this.CommentaireId = CommentaireId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setPublicationId(int publicationId) {
        this.publicationId = publicationId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public static void setCommentaireDao(CommentaireDao commentaireDao) {
        Commentaire.commentaireDao = commentaireDao;
    }

   
}

