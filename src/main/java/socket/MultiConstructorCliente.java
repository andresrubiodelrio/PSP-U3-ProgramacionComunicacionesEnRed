package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MultiConstructorCliente {
    public static void main(String[] args) {
        String host = "127.0.0.1";
        int[] ports = {5000, 5001, 5002, 5003};

        for (int port : ports) {
            try (Socket socket = new Socket(host, port);
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

                // Leer mensaje del servidor
                String serverMsg = in.readLine();
                System.out.println("Servidor en puerto " + port + " dice: " + serverMsg);

                // Enviar mensaje al servidor
                out.println("Hola desde cliente al puerto " + port);

            } catch (IOException e) {
                e.getMessage();
            }
        }
    }
}
