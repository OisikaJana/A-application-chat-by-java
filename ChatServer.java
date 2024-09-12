import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {
    private ServerSocket serverSocket;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public ChatServer(int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started. Waiting for a client to connect...");

            socket = serverSocket.accept();  // Wait for a connection
            System.out.println("Client connected!");

            // Set up streams
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            // Start threads for sending and receiving messages
            new Thread(new ReceiveMessage()).start();
            new Thread(new SendMessage()).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class ReceiveMessage implements Runnable {
        @Override
        public void run() {
            String message;
            try {
                while ((message = in.readLine()) != null) {
                    System.out.println("Client: " + message);
                }
            } catch (IOException e) {
                System.out.println("Connection closed.");
            }
        }
    }

    private class SendMessage implements Runnable {
        @Override
        public void run() {
            BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));
            String message;
            try {
                while (true) {
                    message = consoleInput.readLine();
                    out.println(message);
                    if (message.equalsIgnoreCase("exit")) {
                        socket.close();
                        serverSocket.close();
                        break;
                    }
                }
            } catch (IOException e) {
                System.out.println("Connection closed.");
            }
        }
    }

    public static void main(String[] args) {
        new ChatServer(12345);  // Start the server on port 12345
    }
}
