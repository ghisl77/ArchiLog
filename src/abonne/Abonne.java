package abonne;

import bd.Mediatheque;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;
import java.util.Random;

public class Abonne {

    private Socket socket;
    private int numero;
    private String nom;
    private Date dateNaissance;
    private Mediatheque mediatheque;
    private IDocument currentDocument;  // The document currently borrowed by the Abonne

    private String lastAction = "";  // The last action performed by the Abonne
    public Abonne(int num, String nom, Date dateNaissance){
        numero = num;
        this.nom = nom;
        this.dateNaissance = dateNaissance;
        currentDocument = null;
    }

    public void setMediatheque(Mediatheque mediatheque) {
        this.mediatheque = mediatheque;
    }
    public String getNom() {
        return nom;
    }

    public int getNumero() {
        return numero;
    }
}