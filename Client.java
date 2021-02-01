package clientserver;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket( "", 8189);
            Scanner in = new Scanner(socket.getInputStream());
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Scanner sc = new Scanner(System.in);

            System.out.println("Hello Client!!!");

            new Thread(() -> {
                while (true) {
                    String msg = sc.nextLine();
                    System.out.println("Client: " + msg);
                    out.println(msg);
                }
            }).start();

            while (true) {
                String msg = in.nextLine();

                if (msg.equals("/end")) {
                    System.out.println("Client disconnected");
                    break;
                }

                System.out.println("Server :" + msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
