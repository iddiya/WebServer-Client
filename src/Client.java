import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            // Create a client socket
            Socket clientSocket = new Socket("localhost", 8080);
            System.out.println("Connected to server.");

            // Accept an integer from the keyboard
            Scanner scanner = new Scanner(System.in);
            int clientNumber;
            do {
                System.out.print("Enter an integer between 1 and 100: ");
                clientNumber = scanner.nextInt();
            } while (clientNumber < 1 || clientNumber > 100);

            // Prepare client's message
            String clientName = "Client of Dahun Im";
            String clientMessage = clientName + "," + clientNumber;
            byte[] clientBytes = clientMessage.getBytes();

            // Send client's message to the server
            OutputStream outputStream = clientSocket.getOutputStream();
            outputStream.write(clientBytes);

            // Receive server's reply
            InputStream inputStream = clientSocket.getInputStream();
            byte[] buffer = new byte[1024];
            int bytesRead = inputStream.read(buffer);
            String serverReply = new String(buffer, 0, bytesRead);
            String[] serverData = serverReply.split(",");
            String serverName = serverData[0];
            int serverNumber = Integer.parseInt(serverData[1]);
            int sum = Integer.parseInt(serverData[2]);

            // Print server's name, server number, and sum
            System.out.println("Server: " + serverName);
            System.out.println("Client number: " + clientNumber);
            System.out.println("Server number: " + serverNumber);
            System.out.println("Sum: " + sum);

            // Close the client socket
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}