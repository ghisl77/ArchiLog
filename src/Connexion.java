import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Connexion {
    static final String DB_URL = "jdbc:mysql://localhost/projetarchilog?useSSL=false"; // MySQL
    static final String USER = "root";
    static final String PASS = "root";

    /*public static void main(String[] args) {
        try {
            //Class.forName("oracle.jdbc.OracleDriver"); // Oracle
            Class.forName("com.mysql.jdbc.Driver");  // MySQL
        }
        catch (ClassNotFoundException e1) {
            System.err.print("ClassNotFoundException: ");
            System.err.println(e1.getMessage());
        }
        try {
            // Open connection
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("On est connecté au serveur sur la base aero");

            String queryINSERT = "INSERT INTO abonne VALUES (?, ?, ?)";
            PreparedStatement st2 = conn.prepareStatement(queryINSERT);
            st2.setInt(1, 1);
            st2.setString(2, "Ghislaine");
            st2.setString(3, "2019-01-25");

            int rows = st2.executeUpdate();
            System.out.println("Nombre de lignes insérées: " + rows);

            // Close connection
            conn.close();
            System.out.println("Bye Bye") ;

        }
        catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
        }
    }*/
    public List<IDocument> récupérerDoc() throws SQLException {
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
            System.out.println("On est connecté au serveur sur la base aero");

            List<IDocument> listeDocuments = new ArrayList<>();

            String querySELECT = "SELECT numero, titre, type, NbPages, adulte FROM documents";

            PreparedStatement st = conn.prepareStatement(querySELECT);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                IDocument document;
                // Créer un objet Document pour stocker les données de chaque ligne
                if (rs.getString("type").equals("livre")) {
                    document = new Livre(rs.getInt("numero"), rs.getString("titre"), rs.getInt("NbPages"));
                } else {
                    document = new DVD(rs.getInt("numero"), rs.getString("titre"), rs.getBoolean("adulte"));
                }

                // Ajouter l'objet Document à la liste
                listeDocuments.add(document);
            }

            rs.close();
            return listeDocuments;
        }
        catch (SQLException e) {
            return null;
        }
    }
    public List<Abonne> récupérerAbo() throws SQLException {
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
            System.out.println("On est connecté au serveur sur la base aero");

            List<Abonne> listeDocuments = new ArrayList<>();

            String querySELECT = "SELECT numero FROM abonne";

            PreparedStatement st = conn.prepareStatement(querySELECT);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Abonne abo = new Abonne(rs.getInt("numero"));

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
