package server;

import bd.Mediatheque;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;

public class ServeurReservation implements Runnable{
    private ServerSocket listen_socket;
    private Mediatheque media;

    public ServeurReservation(int port, Mediatheque media, String ip) throws IOException {
        listen_socket = new ServerSocket(port,50, InetAddress.getByName(ip));
        this.media = media;
    }

    public void run() {
        try {
            System.err.println("Lancement du serveur au port " + this.listen_socket.getLocalPort());
            while (true)
                new Thread(new ServiceReservation(listen_socket.accept(), media)).start();
        } catch (IOException e) {
            try {
                this.listen_socket.close();
            } catch (IOException e1) {
            }
            System.err.println("Arrêt du serveur au port " + this.listen_socket.getLocalPort());
        }
    }
}