package server;

import abonne.Abonne;
import abonne.IDocument;
import bd.Mediatheque;

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
            do {
                out.println("Veuiilez saisir votre numero d'abonne");
                numAbonne = Integer.parseInt(in.readLine());
            }while (media.getAbonneByNumero(numAbonne) == null);
            out.println("Connexion reussi");
            out.println("Saisissez 1 si vous voulez emprunter un document ou 2 si vous voulez retourner un document");
            int choix = Integer.parseInt(in.readLine());
            if(choix == 1) {
                out.println("Veuiilez saisir le numero du document que vous voulez emprunter :");
                StringBuffer b = new StringBuffer(in.readLine());
                String[] Message = b.toString().split(" ");
                int nA = Integer.parseInt(Message[0]);
                int numDoc = Integer.parseInt(Message[1]);
                if(media.getAbonneByNumero(numAbonne) == null){

                }
            }
            else{

            }

        } catch (IOException e) {}
    }
}
