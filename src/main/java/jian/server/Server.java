package jian.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ServerSocket serverSocket;

    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }

    private void start() {
        try {
            serverSocket = new ServerSocket(8888);

            receive();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void receive() {
        try {
            Socket client = serverSocket.accept();

            BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
            StringBuilder sb = new StringBuilder();

            sb.append("\r\n");
            String msg = null;
            while ((msg = br.readLine()).length() > 0) {
                sb.append(msg);
                if (msg == null) {
                    break;
                }

            }
            System.out.println(sb.toString().trim());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
