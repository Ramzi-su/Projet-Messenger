# 📩 Projet Messenger

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![MIT License](https://img.shields.io/badge/License-MIT-green?style=for-the-badge)

## 📝 Description

**Projet Messenger** est une application Java en mode console, inspirée des fonctionnalités sociales de Facebook.  
Elle permet aux utilisateurs d’interagir à travers des messages privés, des publications, des commentaires et des réactions.  
Ce projet est construit sur un modèle client-serveur avec une base de données MySQL.

---

## ✨ Fonctionnalités

- 🔐 Authentification des utilisateurs
- 📨 Envoi de messages privés
- 📝 Création de publications
- 💬 Ajout de commentaires
- ❤️ Réactions aux publications
- 🔌 Communication client-serveur via sockets TCP

---

## ⚙️ Technologies utilisées

- Java (JDK)
- Sockets (TCP)
- JDBC (Java Database Connectivity)
- MySQL
- NetBeans
- MySQL Connector/J

---

## 🚀 Installation

### ✅ Prérequis

- Java Development Kit (JDK) installé
- Serveur MySQL opérationnel
- IDE NetBeans (https://netbeans.apache.org/) (recommandé)
- MySQL Connector/J (https://dev.mysql.com/downloads/connector/j/) ajouté au projet

### 🛠️ Étapes

1. Cloner le dépôt :
   git clone https://github.com/Ramzi-su/Projet-Messenger.git

2. Importer le projet dans NetBeans

3. Ajouter le fichier `mysql-connector-java-8.0.xx.jar` dans les bibliothèques du projet

4. Créer la base de données MySQL :
   - Crée une base de données avec les tables nécessaires (voir fichier SQL si fourni)
   - Exemple :
     CREATE DATABASE messenger;

5. Configurer le fichier `config.properties` :
   url=jdbc:mysql://localhost:3306/messenger  
   user=root  
   password=ton_mot_de_passe

---

## ▶️ Exécution

1. Lancer le serveur :  
   Exécuter la classe suivante :  
   src/Presentation/Server.java

2. Lancer le client :  
   Exécuter :  
   src/Dao/Main.java

3. Utiliser la console pour interagir entre utilisateurs

---

## 👤 Auteur

**Ramzi-su**

---

## 📄 Licence

Ce projet est sous licence **MIT**.  
Vous êtes libre de l’utiliser, le modifier et le distribuer.

