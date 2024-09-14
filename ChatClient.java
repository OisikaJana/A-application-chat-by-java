import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClient {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public ChatClient(String serverAddress, int port) {
        try {
            socket = new Socket(serverAddress, port);
            System.out.println("Connected to the server!");

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
                    System.out.println("Server: " + message);
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
                        break;
                    }
                }
            } catch (IOException e) {
                System.out.println("Connection closed.");
            }
        }
    }

    public static void main(String[] args) {
        new ChatClient("127.0.0.1", 12345);  // Connect to server at localhost and port 12345
    }
}
