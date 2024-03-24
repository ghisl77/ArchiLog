public abstract class Document implements IDocument{
    private int numero;
    private String titre;
    private Connexion connexion;

    @Override
    public int numero() {
        return 0;
    }

    @Override
    public Abonne emprunteur() {
        return null;
    }

    @Override
    public Abonne reserveur() {
        return null;
    }

    @Override
    public void reservationPour(Abonne ab) {

    }

    @Override
    public void empruntPar(Abonne ab) {

    }

    @Override
    public void retour() {

    }
}
