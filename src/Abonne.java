import java.util.Date;

public class Abonne implements Runnable{
    private int numero;
    private String nom;
    private Date dateNaissance;
    public Abonne(int num, String nom, Date dateNaissance){
        numero = num;
        this.nom = nom;
        this.dateNaissance = dateNaissance;
    }
    @Override
    public void run() {

    }
}
