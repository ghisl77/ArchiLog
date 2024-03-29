public abstract class Document implements IDocument{
    private int numero;
    private String titre;
    public Document(int num, String titre){
        this.numero = num;
        this.titre = titre;
    }
    @Override
    public int numero() {
        return this.numero;
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
