package clientserver;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    static ServerSocket server;
    static Socket socket;
    static final int PORT = 8189;

    public static void main(String[] args) {
        try {
            server = new ServerSocket(PORT);
            System.out.println("server started");

            socket = server.accept();
            System.out.println("client connected");

            Scanner sc = new Scanner(System.in);
            Scanner in = new Scanner(socket.getInputStream());
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            new Thread(() -> {
                while (true) {
                    String msg = sc.nextLine();
                    System.out.println("Server: " + msg);
                    out.println(msg);
                }
            }).start();

            while (true) {
                String str = in.nextLine();

                if (str.equals("/end")) {
                    System.out.println("Client disconnected");
                    break;
                }

                System.out.println("Client: " + str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
