package bd;

import abonne.Abonne;
import document.DVD;
import document.Livre;
import abonne.IDocument;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Connexion {
    static final String DB_URL = "jdbc:mysql://localhost/projetarchilog?useSSL=false"; // MySQL
    static final String USER = "root";
    static final String PASS = "";

    public List<IDocument> recupererDoc() throws SQLException {
        // Création d'une liste pour stocker les documents récupérés
        try {
            //Class.forName("oracle.jdbc.OracleDriver"); // Oracle
            Class.forName("com.mysql.jdbc.Driver");  // MySQL
        } catch (ClassNotFoundException e1) {
            System.err.print("ClassNotFoundException: ");
            System.err.println(e1.getMessage());
        }
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("On est connecté au serveur sur la base média, on va récupérer les documents");

            List<IDocument> listeDocuments = new ArrayList<>();

            String querySELECT = "SELECT numero, titre, type, NbPages, adulte FROM documents";

            PreparedStatement st = conn.prepareStatement(querySELECT);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                IDocument document;
                // Créer un objet Document.Document pour stocker les données de chaque ligne
                if (rs.getString("type").equals("livre")) {
                    document = new Livre(rs.getInt("numero"), rs.getString("titre"), rs.getInt("NbPages"));
                } else {
                    document = new DVD(rs.getInt("numero"), rs.getString("titre"), rs.getBoolean("adulte"));
                }

                // Ajouter l'objet Document.Document à la liste
                listeDocuments.add(document);
            }

            rs.close();
            return listeDocuments;
        }
        catch (SQLException e) {
            return null;
        }
    }

    public List<Abonne> recupererAbo() throws SQLException {
        // Création d'une liste pour stocker les documents récupérés
        try {
            //Class.forName("oracle.jdbc.OracleDriver"); // Oracle
            Class.forName("com.mysql.jdbc.Driver");  // MySQL
        }
        catch (ClassNotFoundException e1) {
            System.err.print("ClassNotFoundException: ");
            System.err.println(e1.getMessage());
        }
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("On est connecté au serveur sur la base mediatheque, on va récupérer les abonnés");

            List<Abonne> listeDocuments = new ArrayList<>();

            String querySELECT = "SELECT numero, nom, date_naissance FROM abonne";

            PreparedStatement st = conn.prepareStatement(querySELECT);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Abonne abo = new Abonne(rs.getInt("numero"), rs.getString("nom"), rs.getDate("date_naissance"));

                listeDocuments.add(abo);
            }

            rs.close();
            return listeDocuments;
        }
        catch (SQLException e) {
            return null;
        }
    }
}
