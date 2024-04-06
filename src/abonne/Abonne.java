package abonne;

import bd.Mediatheque;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;
import java.util.Random;

public class Abonne implements Runnable{

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
    private void sendRequest(String serverAddress, int serverPort, int abonne, int doc) {
        try {
            this.socket = new Socket(serverAddress, serverPort);
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            output.println(abonne + " " + doc);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        IDocument doc = null;
        try {
            doc = mediatheque.getRandomDoc();
            while (true) {

                Thread.sleep(1000);  // Pause for 1 second
            }
        } catch (Exception e) {
            System.out.println("Thread " + Thread.currentThread().getId() + ": " + this.nom + " a terminé");
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}