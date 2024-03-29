public class Livre extends Document{
    private int nbPages;
    public Livre(int num, String titre,int nbPages){
        super(num,titre);
        this.nbPages = nbPages;
    }
}
