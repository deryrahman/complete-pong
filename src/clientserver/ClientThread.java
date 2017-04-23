package clientserver;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientThread extends Thread {

    private String clientName = null;
    private Socket clientSocket = null;
    private Scanner is = null;
    private PrintStream os = null;

    private ClientThread[] threads;
    private int maxClientsCount;

    /**
     * Constructor
     * @param clientSocket : invoke current socket
     * @param threads : invoke thread array
     */
    public ClientThread(Socket clientSocket, ClientThread[] threads) {
        this.clientSocket = clientSocket;
        this.threads = threads;
        maxClientsCount = threads.length;
    }

    /**
     * Catch command, and transfer to opponent
     */
    public void commandTransferProtocol(){
        while (true) {
            String line  = is.nextLine();
            if (line.startsWith("/q")) {
                break;
            }
            synchronized (this) {
                for (int i = 0; i < maxClientsCount; i++) {
                    if (threads[i] != null && threads[i].clientName != null && threads[i]!=this) {
                        threads[i].os.println("<" + clientName + "> " + line);
                    }
                }
            }
        }
    }

    /**
     * Client Thread is running. Waiting for input name, and command.
     * To exit type /q, it will send to server and catch in Client class
     * as "Leave"
     */
    public void run() {
        try {
            is = new Scanner(clientSocket.getInputStream());
            os = new PrintStream(clientSocket.getOutputStream());
            String name;
            os.println("Nama : ");
            name = is.nextLine().trim();
            clientName = name;

            synchronized (this) {
                for (int i = 0; i < maxClientsCount; i++) {
                    if (threads[i] != null && threads[i] != this) {
                        threads[i].os.println("New player opponent : " + name);
                    }
                }
            }

            // Catch command, and transfer to opponent
            commandTransferProtocol();

            // Exit when type /q
            os.println("Leave");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}