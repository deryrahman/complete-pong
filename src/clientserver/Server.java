package clientserver;

import java.io.IOException;
import java.io.PrintStream;
import java.net.*;
import java.util.Enumeration;

public class Server {
    private static ServerSocket serverSocket = null;
    private static Socket clientSocket = null;
    private static int playerCount = 0;

    private static final int maxClient = 2;
    private static final ClientThread[] threads = new ClientThread[maxClient];

    private static int portNumber;
    private static String ip;

    public static void main(String[] args) {
        // Get port. Default 8888
        portNumber = 8888;
        if(args.length>0) portNumber = Integer.valueOf(args[0]).intValue();

        // Get ip address. Default localhost
        ip = getIpAddr();

        // Print ip address host and port
        System.out.println( "Host : " + ip + "\n"
                        + "Port : " + portNumber);

        // Open server socket
        openSocket();

        // Create client socket on the fly
        createClientSocketRunTime();
    }

    /**
     * Get ip address in a network
     */
    public static String getIpAddr(){
        ip = "localhost";
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface iface = interfaces.nextElement();
                if (iface.isLoopback() || !iface.isUp()) continue;

                Enumeration<InetAddress> addresses = iface.getInetAddresses();
                while(addresses.hasMoreElements()) {
                    InetAddress addr = addresses.nextElement();
                    ip = addr.getHostAddress();
                    if(ip.matches("(\\d{1,3}\\.){3}\\d{1,3}")) break;
                }
            }
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
        return ip;
    }

    /**
     * Open server socket
     */
    private static void openSocket() {
        try {
            serverSocket = new ServerSocket(portNumber);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Create client socket on the fly
     */
    private static void createClientSocketRunTime() {
        while (true){
            try {
                clientSocket = serverSocket.accept();
                int i = 0;
                for(; i < maxClient; i++){
                    if(threads[i] != null) continue;
                    threads[i] = new ClientThread(clientSocket,threads);
                    threads[i].start();
                    playerCount++;
                    System.out.println(threads[i].getName() + " | " + " Player " + playerCount + " :");
                    break;
                }
                if(i==maxClient){
                    PrintStream os = new PrintStream(clientSocket.getOutputStream());
                    os.println("Server penuh");
                    os.close();
                    clientSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
