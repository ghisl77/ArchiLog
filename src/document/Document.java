package document;

import abonne.Abonne;
import abonne.IDocument;

import java.util.Date;
import java.util.Random;

public abstract class Document implements IDocument {
    private int numero;
    private String titre;
    private Abonne emprunteur;
    private Abonne reserveur;
    public Document(int num, String titre){
        this.numero = num;
        this.titre = titre;
        emprunteur = null;
        reserveur = null;
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
    public void reservationPour(Abonne ab) {
        assert (reserveur != null);
        reserveur = ab;
    }

    @Override
    public void empruntPar(Abonne ab) {
        assert (emprunteur != null);
        emprunteur = ab;
    }

    @Override
    public void retour() {
        emprunteur = null;
    }
    public boolean verifieAge(Date dateNais){
        return true;
    }
    @Override
    public String getTitre(){
        return this.titre;
    }
    @Override
    public boolean occupe(Abonne abo){
        return emprunteur != null || (reserveur != null && reserveur != abo);
    }

}
