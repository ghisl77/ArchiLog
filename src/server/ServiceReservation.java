package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServiceReservation implements Runnable{
    private final Socket client;

    ServiceReservation(Socket socket) {
        this.client = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);
            out.println("Veuillez reserver un document parmi la liste suivante :");
            out.println();
        } catch (IOException e) {}
    }
}
