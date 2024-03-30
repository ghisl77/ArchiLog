package bd;

import abonne.Abonne;
import abonne.IDocument;
import bd.Connexion;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    public static IDocument getRandomDoc(){

    }
}
