import abonne.Abonne;
import bd.Mediatheque;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        Mediatheque med = new Mediatheque();

        // Get the list of Abonne from the Mediatheque
        List<Abonne> abonnes = med.getAbonnes();

        // Wait for the servers to start
        try {
            Thread.sleep(5000);  // Wait for 5 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Create and start a new Thread for each Abonne
        for (Abonne abonne : abonnes) {
            Thread thread = new Thread(abonne);
            thread.start();
        }
    }
}