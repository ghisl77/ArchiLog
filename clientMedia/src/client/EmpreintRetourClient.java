package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class EmpreintRetourClient {
    public static void main(String[] args) {
        String serverAddress = "127.0.0.1"; // Adresse IP du serveur
        int serverPort = 4000; // Port du serveur

        try (Socket socket = new Socket(serverAddress, serverPort)) {
            System.out.println("Connected to Borrow/Return Server successfully.");
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            String str = null;
            Scanner scanner = new Scanner(System.in);
            while(str.equals("Connexion reussie")==false){
                str = in.readLine();
                System.out.println(str);
                str = scanner.nextLine();
                out.println(str);
            }
            str = in.readLine();
            System.out.println(str);
            str = scanner.nextLine();
            out.println(str);
            str = in.readLine();
            System.out.println(str);
            str = scanner.nextLine();
            out.println(str);
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

