package client;

import java.io.*;
import java.net.*;

public class BorrowReturnClient {
    public static void main(String[] args) {
        String serverAddress = "127.0.0.1"; // Adresse IP du serveur
        int serverPort = 4000; // Port du serveur

        try (Socket socket = new Socket(serverAddress, serverPort)) {
            System.out.println("Connected to Borrow/Return Server successfully."); // Message de connexion réussie
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader serverInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            // Logic to send borrow/return request to server

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

