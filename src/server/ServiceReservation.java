package server;

import abonne.Abonne;
import abonne.IDocument;
import bd.Mediatheque;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class ServiceReservation implements Runnable{
    private final Socket client;
    private Mediatheque media;

    ServiceReservation(Socket socket, Mediatheque media) {
        this.client = socket;
        this.media = media;
    }
    private class FinService extends TimerTask {
        private Thread thread;
        private IDocument doc;

        public FinService(Thread thread,IDocument doc) {
            this.doc = doc;

            this.thread = thread;
        }

        @Override
        public void run() {
            doc.retour();
            media.getConnexion().retourDoc(doc);
            System.out.println("doc retourner");
            this.thread.interrupt();
        }
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
            out.println(" ");
            out.println("Veuillez reserver un document parmi la liste en écrivant son numéro :");
            int numDoc =Integer.parseInt(in.readLine());
            if(media.getDocumentByNumero(numDoc)== null){
                out.println("document inexistant");
            }
            else{
                doc = media.getDocumentByNumero(numDoc);
                synchronized(doc){
                    if(doc.reserveur()==null || doc.reserveur()==abo){
                        if(doc.verifieAge(abo.getDate())) {
                            doc.reservationPour(abo);
                            Timer timer = new Timer();
                            timer.schedule(new FinService(Thread.currentThread(),doc),2 * 60 * 60 * 1000);
                            out.println("vous avez bien reservee :" + doc.getTitre());
                            media.getConnexion().reservationDoc(doc, abo);
                        }
                        else{
                            out.println("vous n’avez pas l’âge pour reserver ce DVD : "+ doc.getTitre());
                        }
                    }
                    else{
                        out.println("ce " + doc.getClass().getSimpleName() +  " est réservé jusqu’à "+ doc.getReservationTime());
                    }
                }
            }
            client.close();
        } catch (IOException e) {}
    }
}
