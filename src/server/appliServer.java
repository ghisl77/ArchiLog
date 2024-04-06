package server;

import bd.Mediatheque;

public class appliServer {
        private final static int PORT1 = 3000;
    private final static int PORT2 = 4000;
    private final static String ipAddress = "127.0.0.1";

        public static void main(String[] args) throws Exception {
            Mediatheque media = new Mediatheque();
            new Thread(new ServeurReservation(PORT1,media,ipAddress)).start();
            new Thread(new ServeurER(PORT2,media,ipAddress)).start();

        }
}
