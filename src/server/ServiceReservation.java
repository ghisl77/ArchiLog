package server;

import abonne.Abonne;
import abonne.IDocument;
import bd.Mediatheque;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServiceReservation implements Runnable{
    private final Socket client;
    private Mediatheque media;

    ServiceReservation(Socket socket, Mediatheque media) {
        this.client = socket;
        this.media = media;
    }
    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);
            int numAbonne;
            Abonne abo ;
            IDocument doc;
            out.println("Veuiilez saisir votre numero d'abonne");
            numAbonne = Integer.parseInt(in.readLine());
            abo = media.getAbonneByNumero(numAbonne);
            out.println("Connexion reussi");
            out.println(media.toStringDoc());
            out.println("Veuillez reserver un document parmi la liste suivante en écrivant son numéro :");
            int numDoc =Integer.parseInt(in.readLine());
            if(media.getDocumentByNumero(numDoc)== null){
                out.println("document inexistant");
            }
            else{
                doc = media.getDocumentByNumero(numDoc);
                if(doc.reserveur()==null || doc.reserveur()==abo){
                    doc.reservationPour(abo);
                    out.println("reservation reussie");
                }
                else{
                    out.println("déja reservé");
                }
            }
            client.close();
        } catch (IOException e) {}
    }
}
