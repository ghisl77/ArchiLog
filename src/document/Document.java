package document;

import abonne.Abonne;
import abonne.IDocument;

import java.util.Date;
import java.util.Random;
import java.util.Timer;

public abstract class Document implements IDocument {
    private int numero;
    private String titre;
    private Abonne emprunteur;
    private Abonne reserveur;
    private Date reservationTime;
    public Document(int num, String titre){
        this.numero = num;
        this.titre = titre;
        emprunteur = null;
        reserveur = null;
        reservationTime = null;
    }
    @Override
    public int numero() {
        return this.numero;
    }

    @Override
    public Abonne emprunteur() {
        return emprunteur;
    }

    @Override
    public Abonne reserveur() {
        return reserveur;
    }

    @Override
    public synchronized void reservationPour(Abonne ab) {
        // Only allow reservation if the document is not already reserved or if it's reserved by a different Abonne
        if (reserveur == null || !reserveur.equals(ab)) {
            reserveur = ab;
            reservationTime = new Date();  // Set the reservation time
        }
    }


    public synchronized void cancelReservation() {
        if (emprunteur == null && reservationTime != null && new Date().getTime() - reservationTime.getTime() > 20 * 1000) { // 20 seconds
            System.out.println("Reservation for " + this.titre + " by " + reserveur.getNom() + " is being cancelled after 20 seconds.");
            reserveur = null;
            reservationTime = null;
        }
    }

    @Override
    public synchronized void empruntPar(Abonne ab) {
        assert (emprunteur == null);
        if (reserveur == null || reserveur == ab) {
            emprunteur = ab;
            reserveur = null;
            reservationTime = null;
        }
    }

    @Override
    public synchronized void retour() {
        emprunteur = null;
        reserveur = null;
        reservationTime = null;
    }
    public boolean verifieAge(Date dateNais){
        return true;
    }
    public String getTitre(){
        return this.titre;
    }
    public boolean occupe(Abonne abo){
        return emprunteur != null || (reserveur != null && reserveur != abo);
    }

}