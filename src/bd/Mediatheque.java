package bd;

import abonne.Abonne;
import abonne.IDocument;
import bd.Connexion;

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
        tabDoc = new ArrayList<>() ;
        tabAbo = new ArrayList<>() ;
        tabDoc = con.recupererDoc();
        tabAbo = con.recupererAbo();
        System.out.println(tabDoc);
        System.out.println(tabAbo);
    }
    public  IDocument getRandomDoc(){
        Random rand = new Random();
        int index = rand.nextInt(tabDoc.size());
        return tabDoc.get(index);
    }
}
