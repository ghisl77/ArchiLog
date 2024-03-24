import java.sql.*;

public class Connexion {
    static final String DB_URL = "jdbc:mysql://localhost/projetarchilog?useSSL=false"; // MySQL
    static final String USER = "root";
    static final String PASS = "root";

    public static void main(String[] args) {
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
    }
}
