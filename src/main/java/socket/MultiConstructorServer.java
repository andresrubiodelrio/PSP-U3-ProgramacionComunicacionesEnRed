package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiConstructorServer {

    public static void main(String[] args) {
        try {
            // ============================
            // Constructor ServerSocket()
            // ============================
            ServerSocket server1 = new ServerSocket();
            server1.bind(new InetSocketAddress(5000)); // bind explícito
            System.out.println("Servidor 1 escuchando en " + server1.getLocalPort());
            new Thread(() -> handleClientes(server1, "Servidor1")).start();

            // ============================
            // Constructor ServerSocket(int port)
            // ============================
            ServerSocket server2 = new ServerSocket(5001);
            System.out.println("Servidor 2 escuchando en " + server2.getLocalPort());
            new Thread(() -> handleClientes(server2, "Servidor2")).start();

            // ============================
            // Constructor ServerSocket(int port, int backlog)
            // ============================
            ServerSocket server3 = new ServerSocket(5002, 10); // backlog 10
            System.out.println("Servidor 3 escuchando en " + server3.getLocalPort());
            new Thread(() -> handleClientes(server3, "Servidor3")).start();

            // ============================
            // Constructor ServerSocket(int port, int backlog, InetAddress bindAddr)
            // ============================
            InetAddress localAddr = InetAddress.getByName("127.0.0.1");
            ServerSocket server4 = new ServerSocket(5003, 5, localAddr);
            System.out.println("Servidor 4 escuchando en " + server4.getLocalPort() +
                    " y dirección " + server4.getInetAddress());
            new Thread(() -> handleClientes(server4, "Servidor4")).start();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    // ============================
    // Manejo de clientes
    // ============================
    private static void handleClientes(ServerSocket server, String serverName) {
        while (true) {
            try {
                Socket cliente = server.accept(); // acepta conexión
                System.out.println(serverName + " aceptó cliente: " + cliente.getRemoteSocketAddress());

                // Responder al cliente
                PrintWriter out = new PrintWriter(cliente.getOutputStream(), true);
                out.println("Hola desde " + serverName + "!");

                // Leer mensaje del cliente (opcional)
                BufferedReader in = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
                String msg = in.readLine();
                if (msg != null) {
                    System.out.println(serverName + " recibió: " + msg);
                }


                cliente.close();

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}


