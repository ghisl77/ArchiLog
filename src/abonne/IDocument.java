package abonne;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Timer;

public interface IDocument {
    int numero();
    // return null si pas emprunté ou pas réservé
    Abonne emprunteur() ; // Abonné qui a emprunté ce document
    Abonne reserveur() ; // Abonné qui a réservé ce document
    // precondition : ni réservé ni emprunté
    void reservationPour(Abonne ab) ;
    // precondition : libre ou réservé par l’abonné qui vient emprunter
    void empruntPar(Abonne ab);
    // retour d’un document ou annulation d‘une réservation
    void retour();
    boolean verifieAge(Date dateNais);
    String getTitre();
}
