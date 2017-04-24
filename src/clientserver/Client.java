package clientserver;

import java.io.PrintStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client implements Runnable {

    private static Socket clientSocket = null;
    private static PrintStream os = null;
    private static Scanner is = null;

    private static BufferedReader inputLine = null;
    private static boolean closed = false;

    private static int portNumber;
    private static String host;

    public static void main(String[] args) {

        // Get port and host. Default port 8888, default host localhost
        portNumber = 8888;
        host = "localhost";
        if (args.length > 2) {
            host = args[0];
            portNumber = Integer.valueOf(args[1]).intValue();
        }

        // Print host and portnumber
        System.out.println( "Host : " + host + "\n"
                + "Port : " + portNumber);

        // Open socket on a given host and port. And prepare inputLine, output stream, and input stream
        openClientSocket();

        // Wait for input command
        inputCommandWaiting();
    }

    /**
     * Try to open client socket
     */
    public static void openClientSocket() {
        try {
            clientSocket = new Socket(host, portNumber);
            inputLine = new BufferedReader(new InputStreamReader(System.in));
            os = new PrintStream(clientSocket.getOutputStream());
            is = new Scanner(clientSocket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Wait for command
     */
    public static void inputCommandWaiting() {
        if (clientSocket != null && os != null && is != null) {
            try {
                // Create a thread to read from the server
                new Thread(new Client()).start();
                while (!closed) {
                    os.println(inputLine.readLine().trim());
                }
                os.close();
                is.close();
                clientSocket.close();
            } catch (IOException e) {
                System.err.println("IOException:  " + e);
            }
        }
    }

    // setter
    public static void setPortNumber(int p){
        portNumber = p;
    }
    public static void setHost(String h){
        host = h;
    }
    // getter
    public static int getPortNumber(){
        return portNumber;
    }
    public static String getHost(){
        return host;
    }

    /**
     * Keep on reading from the socket till we receive "Leave" from the
     * server
     */
    public void run() {
        String responseLine;
        while (true) {
            responseLine = is.nextLine();
            System.out.println(responseLine);
            if (responseLine.indexOf("Leave") != -1) {
                break;
            }
        }
        closed = true;
    }
}