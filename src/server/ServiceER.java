package server;

import abonne.Abonne;
import abonne.IDocument;
import bd.Mediatheque;
import document.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class ServiceER implements Runnable{
    private final Socket client;
    private Mediatheque media;

    ServiceER(Socket socket, Mediatheque media) {
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
            out.println("Saisissez 1 si vous voulez emprunter un document ou 2 si vous voulez retourner un document");
            int choix = Integer.parseInt(in.readLine());
            if(choix == 1) {
                out.println("Veuiilez saisir le numero du document que vous voulez emprunter :");
            }
            if(choix == 2) {
                out.println("Veuiilez saisir le numero du document que vous voulez retourner :");
            }
                int numDoc = Integer.parseInt(in.readLine());
                if(media.getDocumentByNumero(numDoc) == null){
                    out.println("document inexistant");
                }
                else {
                    doc = media.getDocumentByNumero(numDoc);
                    if(choix == 1){
                        if(doc.emprunteur()==null && (doc.reserveur() == media.getAbonneByNumero(numAbonne) || doc.reserveur() == null)) {
                            doc.empruntPar(abo);
                            out.println("reservation faites avec succes");
                            media.getConnexion().empruntDoc(doc, abo);
                        }
                        else{
                            out.println("déja emprunter ou reserver");
                        }
                    }
                    if(choix == 2) {
                            doc.retour();
                            out.println("retour fais avec succes");
                            media.getConnexion().retourDoc(doc);
                    }
                }
            client.close();
            } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
