package bd;

import abonne.Abonne;
import abonne.IDocument;

import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Mediatheque {
    private Connexion con;
    private List<IDocument> tabDoc;
    private List<Abonne> tabAbo;

    public Mediatheque() throws SQLException {
        con = new Connexion();
        tabDoc = new ArrayList<>();
        tabAbo = new ArrayList<>();
        tabDoc = con.recupererDoc();
        tabAbo = con.recupererAbo();

        // Pass the Mediatheque instance to the Abonne instances
        for (Abonne abonne : tabAbo) {
            abonne.setMediatheque(this);
        }

        System.out.println(tabDoc);
        System.out.println(tabAbo);
        System.out.println("Mediatheque crée avec succès");
    }

    public IDocument getRandomDoc(){
        Random rand = new Random();
        int index = rand.nextInt(tabDoc.size());
        return tabDoc.get(index);
    }


    public List<Abonne> getAbonnes() {
        return tabAbo;
    }
    public Abonne getAbonneByNumero(int numero) {
        for (Abonne abonne : tabAbo) {
            if (abonne.getNumero() == numero) {
                return abonne;
            }
        }
        return null;  // Return null if no matching Abonne is found
    }

    public IDocument getDocumentByNumero(int numero) {
        for (IDocument document : tabDoc) {
            if (document.numero() == numero) {
                return document;
            }
        }
        return null;  // Return null if no matching IDocument is found
    }

}