import java.io.*;
import java.net.*;

public class DateClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 6013)) {
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String serverDate = input.readLine();
            System.out.println("Server date: " + serverDate);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
