/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Presentation;

import java.io.*;
import java.net.*;

import metier.MessagePrive;
import metier.User;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import metier.Commentaire;
import metier.Publication;
import metier.Reaction;
import metier.Request;

public class Server {

    private static Map<Integer, ObjectOutputStream> clients = new HashMap<>();

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(4000);

        while (true) {
            Socket socket = serverSocket.accept();
            new Thread(new ClientHandler(socket)).start();
        }
    }

    static class ClientHandler implements Runnable {

        private Socket socket;
        private ObjectInputStream ois;
        private ObjectOutputStream oos;
        private User loggedInUser;

        public ClientHandler(Socket socket) throws IOException {
            this.socket = socket;
            this.oos = new ObjectOutputStream(socket.getOutputStream());
            this.ois = new ObjectInputStream(socket.getInputStream());
        }

        @Override
        public void run() {
            try {
                while (true) {
                    Object receivedObject = ois.readObject();

                    if (receivedObject instanceof User) {
                        handleUserLoginOrRegistration(receivedObject);
                    }

                    if (receivedObject instanceof MessagePrive) {
                        handleMessagePrive(receivedObject);
                    }
                  if (receivedObject instanceof Reaction) {
                        handleReaction(receivedObject);
                    }
                    if (receivedObject instanceof String) {
                        if(receivedObject.equals("Acceuil"))
                            handleaAcceuil(receivedObject);
                            else
                        handleGetMessagesCommand(receivedObject);
                    }
                     
                    if(receivedObject instanceof Publication)
                      handlepublication(receivedObject);
                }
            } catch (IOException | ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            } finally {
                closeResources();
            }
        }

        private void handleUserLoginOrRegistration(Object receivedObject) throws IOException, SQLException, ClassNotFoundException {
            User user = (User) receivedObject;
            if (user.getUserId() == 0) {
                boolean status = user.register();
                oos.writeObject(status ? "Enregistrement réussi" : "Échec de l'enregistrement");
            } else {
                loggedInUser = User.login(user.getUsername(), user.getPassword());

                if (loggedInUser != null) {
                    clients.put(loggedInUser.getUserId(), oos);
                    oos.writeObject(loggedInUser);
                } else {
                    oos.writeObject(null);
                }
            }
        }

        private void handleMessagePrive(Object receivedObject) throws IOException, ClassNotFoundException, SQLException {
            MessagePrive message = (MessagePrive) receivedObject;
            int userId = message.getSenderID();

            if (!clients.containsKey(userId)) {
                clients.put(userId, oos);
            }

            ObjectOutputStream receiverOos = clients.get(message.getReceiverID());
            if (receiverOos != null) {
                message.setMessagePriveId(message.insert());
                receiverOos.writeObject(message);
            }
        }

                private void handlepublication(Object receivedObject) throws IOException, ClassNotFoundException, SQLException {
            Publication pub = (Publication) receivedObject;

           

                pub.setUserId(pub.insert());
                 ObjectOutputStream receiverOos = null;
                receiverOos.writeObject(pub);
            
        }

                private void handleReaction(Object receivedObject) throws IOException, ClassNotFoundException, SQLException {
            Reaction rec = (Reaction) receivedObject;

           

                rec.setUserId(rec.insert());
                 ObjectOutputStream receiverOos = null;
                receiverOos.writeObject(rec);
            
        }
                  private void handleComment(Object receivedObject) throws IOException, ClassNotFoundException, SQLException {
            Commentaire rec = (Commentaire) receivedObject;

           

                rec.setUserId(rec.insert());
                 ObjectOutputStream receiverOos = null;
                receiverOos.writeObject(rec);
            
        }
                  
                  
           

private void handleSendFriendRequest() throws IOException, ClassNotFoundException, SQLException {
    // Demander l'ID de l'utilisateur destinataire
    oos.writeObject("Entre ID of user that you want send to him friendrequest : ");
    
    // Lire l'ID de l'utilisateur destinataire depuis le client
    String input = (String) ois.readObject();

    // Valider l'entrée de l'utilisateur
    int recipientId;
    try {
        recipientId = Integer.parseInt(input.trim());
        if (recipientId <= 0) {
            throw new NumberFormatException();
        }
    } catch (NumberFormatException e) {
        // Gérer les erreurs de format d'entrée
        oos.writeObject("choose right ID.");
        return;
    }

    // Insérer la demande d'ami dans la table Request
    Request request = new Request(loggedInUser.getUserId(), recipientId);
    try {
        request.sendFriendRequest();
        oos.writeObject("request sended to use with ID " + recipientId);
    } catch (SQLException e) {
        // Gérer les erreurs de base de données
        oos.writeObject(" errors try later");
    }
}



                  
                  

        private void handleGetMessagesCommand(Object receivedObject) throws IOException, SQLException, ClassNotFoundException {
            String command = (String) receivedObject;

            if (command.startsWith("getmessages")) {
                int otherUserID = Integer.parseInt(command.split(" ")[1]);
                List<MessagePrive> messages = MessagePrive.getMessagesBetweenUsers(loggedInUser.getUserId(), otherUserID);
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(oos));
                PrintWriter pw = new PrintWriter(bw, true);

                for (MessagePrive message : messages) {
                    pw.println(message.toString()); // Envoyer chaque message individuellement en tant que chaîne de caractères
                }
                pw.println("EndOfMessages"); // Envoyer "EndOfMessages" pour indiquer la fin des messages
            }
        }




        private void handleaAcceuil(Object receivedObject) throws IOException, SQLException, ClassNotFoundException {
            String command = (String) receivedObject;

                List<Publication> pubs = Publication.getAllPublications();
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(oos));
                PrintWriter pw = new PrintWriter(bw, true);

                for (Publication pub : pubs) {
                    pw.println(pub.toString()); // Envoyer chaque message individuellement en tant que chaîne de caractères
                }
                pw.println("EndOfMessages"); // Envoyer "EndOfMessages" pour indiquer la fin des messages
            
        }

        private void closeResources() {
            try {
                ois.close();
                oos.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}