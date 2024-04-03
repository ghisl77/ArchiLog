package server;

import java.io.*;
import java.net.*;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import abonne.Abonne;
import abonne.IDocument;
import bd.Mediatheque;

public class BorrowReturnServer {
    public static void main(String[] args) throws SQLException {
        String serverAddress = "127.0.0.1"; // Adresse IP sur laquelle le serveur écoute
        int serverPort = 4000; // Port sur lequel le serveur écoute
        Mediatheque med = new Mediatheque();

        ExecutorService executor = Executors.newFixedThreadPool(10); // create a thread pool with 10 threads
        try (ServerSocket serverSocket = new ServerSocket(serverPort, 50, InetAddress.getByName(serverAddress))) {
            System.out.println("Le serveur d'emprunt/retour fonctionne sur " + serverAddress + ":" + serverPort + "...");
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.printf("[%s] Connexion acceptée de %s\n", Thread.currentThread().getName(), socket.getInetAddress());
                Thread.sleep(500); // Add delay
                executor.execute(new BorrowReturnHandler(socket, med)); // use executor to run the task
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class BorrowReturnHandler extends Thread {
    private Socket socket;
    private Mediatheque mediatheque;

    public BorrowReturnHandler(Socket socket, Mediatheque mediatheque) {
        this.socket = socket;
        this.mediatheque = mediatheque;
    }

    public void run() {
        try {
            System.out.printf("[%s] Gestion de l'emprunt/retour pour %s\n", Thread.currentThread().getName(), socket.getInetAddress());
            Thread.sleep(500); // Add delay
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String request = input.readLine();
            String[] parts = request.split(" ", 2);
            int abonneNumero = Integer.parseInt(parts[0]);
            int docNumero = Integer.parseInt(parts[1]);

            Abonne abonne = mediatheque.getAbonneByNumero(abonneNumero);
            IDocument doc = mediatheque.getDocumentByNumero(docNumero);

            if (doc != null && abonne != null) {
                if (doc.occupe(abonne)) {
                    doc.retour();
                    System.out.printf("[%s] Document intitulé %s retourné par %s\n", Thread.currentThread().getName(), doc.getTitre(), abonne.getNom());
                    Thread.sleep(500); // Add delay
                } else {
                    doc.empruntPar(abonne);
                    System.out.printf("[%s] Document intitulé %s emprunté par %s\n", Thread.currentThread().getName(), doc.getTitre(), abonne.getNom());
                    Thread.sleep(500); // Add delay
                }
            }
            socket.close();
            System.out.printf("[%s] Fin de la gestion de l'emprunt/retour pour %s\n", Thread.currentThread().getName(), socket.getInetAddress());
            Thread.sleep(500); // Add delay

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}