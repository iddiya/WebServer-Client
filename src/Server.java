import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Server {
    public static void main(String[] args) {
        try {
            // Create a server socket
            ServerSocket serverSocket = new ServerSocket(8080);

            System.out.println("Server started and waiting for connections...");

            while (true) {
                // Wait for a client to connect
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());

                // Receive client's message
                InputStream inputStream = clientSocket.getInputStream();
                byte[] buffer = new byte[1024];
                int bytesRead = inputStream.read(buffer);
                String clientMessage = new String(buffer, 0, bytesRead);
                String[] clientData = clientMessage.split(",");
                String clientName = clientData[0];
                int clientNumber = Integer.parseInt(clientData[1]);

                // Generate server's number
                int serverNumber = new Random().nextInt(100) + 1;

                // Calculate the sum
                int sum = clientNumber + serverNumber;

                // Prepare server's reply
                String serverName = "Server of Dahun Im";
                String replyMessage = serverName + "," + serverNumber + "," + sum;
                byte[] replyBytes = replyMessage.getBytes();

                // Print client's name and server's name
                System.out.println("Client: " + clientName);
                System.out.println("Server: " + serverName);

                // Send server's reply to the client
                OutputStream outputStream = clientSocket.getOutputStream();
                outputStream.write(replyBytes);

                // Close the client connection
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}