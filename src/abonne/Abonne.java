package abonne;

import bd.Mediatheque;

import java.util.Date;

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

    }
}
