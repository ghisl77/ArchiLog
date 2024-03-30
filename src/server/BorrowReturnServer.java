package server;

import java.io.*;
import java.net.*;

public class BorrowReturnServer {
    public static void main(String[] args) {
        String serverAddress = "127.0.0.1"; // Adresse IP sur laquelle le serveur écoute
        int serverPort = 4000; // Port sur lequel le serveur écoute

        try (ServerSocket serverSocket = new ServerSocket(serverPort, 50, InetAddress.getByName(serverAddress))) {
            System.out.println("Borrow/Return Server is running on " + serverAddress + ":" + serverPort + "...");
            while (true) {
                Socket socket = serverSocket.accept();
                new BorrowReturnHandler(socket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class BorrowReturnHandler extends Thread {
    private Socket socket;

    public BorrowReturnHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            // Logic to handle borrow/return requests

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
