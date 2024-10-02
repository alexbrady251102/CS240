import java.io.*;
import java.net.*;

public class DateServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(6013)) {
            int connectionCount = 0; // keep count of connections made
            System.out.println("Server is running...");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                connectionCount++;
                WorkerThread workerThread = new WorkerThread(clientSocket, connectionCount);
                workerThread.start();
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

class WorkerThread extends Thread {
    private Socket clientSocket;
    private int connectionNum;

    public WorkerThread(Socket clientSocket, int connectionNum) {
        this.clientSocket = clientSocket;
        this.connectionNum = connectionNum;
    }

    public void run() {
        try {
            System.out.println("Client " + connectionNum + " connected.");
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println(new java.util.Date().toString());
            System.out.println("Finished processing client " + connectionNum);
            clientSocket.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
