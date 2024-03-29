package Document;

import Abonne.Abonne;
import Abonne.IDocument;

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
}
