package abonne;

import bd.Mediatheque;

import java.util.Date;
import java.util.Random;

public class Abonne implements Runnable{
    private int numero;
    private String nom;
    private Date dateNaissance;
    private Mediatheque mediatheque;
    public Abonne(int num, String nom, Date dateNaissance){
        numero = num;
        this.nom = nom;
        this.dateNaissance = dateNaissance;
    }
    @Override
    public void run() {
        IDocument doc = mediatheque.getRandomDoc();
        try{
            while (true) {
                Random rand = new Random();
                int index = rand.nextInt(3);
                synchronized (doc) {
                    while (doc.occupe(this)) {
                        System.out.println(doc.getTitre() + " occupe");
                        doc.wait();
                    }
                    if(index == 0) {
                        doc.reservationPour(this);
                        System.out.println(this.nom + " a reserver " + doc.getTitre());
                    } else if (index == 1) {
                        doc.empruntPar(this);
                        System.out.println(this.nom + " a emprunter " + doc.getTitre());
                    }
                    else
                        doc.retour();
                    System.out.println(this.nom + " a retoune " + doc.getTitre());
                    doc.notifyAll();
                }
            }
        } catch (InterruptedException e) {
            System.out.println(this.nom + " rend " + doc.getTitre());
        }
    }
}
