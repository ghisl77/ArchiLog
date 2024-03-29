import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        Connexion con = new Connexion();
        List<IDocument> tabDoc = new ArrayList<>() ;
        List<Abonne> tabAbo = new ArrayList<>() ;
        tabDoc = con.récupérerDoc();
        tabAbo = con.récupérerAbo();
        System.out.println(tabDoc);
        System.out.println(tabAbo);
    }
}
