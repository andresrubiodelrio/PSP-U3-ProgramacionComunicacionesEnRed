package socketupdperdida;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class UDPClientConReintentos {

    public static void main(String[] args) {
        final int PUERTO_SERVIDOR = 5000;
        final String HOST_SERVIDOR = "localhost";
        final int MAX_REINTENTOS = 5; // máximo de intentos por paquete
        final int TIMEOUT_MS = 2000;  // espera de respuesta en milisegundos

        try {
            DatagramSocket socket = new DatagramSocket();
            socket.setSoTimeout(TIMEOUT_MS); // timeout para recibir

            InetAddress serverAddress = InetAddress.getByName(HOST_SERVIDOR);
            Scanner scanner = new Scanner(System.in);

            System.out.println("Cliente UDP con reintentos. Escribe mensajes:");

            while (true) {
                System.out.print("> ");
                String mensaje = scanner.nextLine();
                if (mensaje.equalsIgnoreCase("salir")) break;

                byte[] datos = mensaje.getBytes();
                DatagramPacket packet = new DatagramPacket(datos, datos.length, serverAddress, PUERTO_SERVIDOR);

                boolean recibido = false;
                int intentos = 0;

                while (!recibido && intentos < MAX_REINTENTOS) {
                    socket.send(packet); // enviar paquete
                    intentos++;

                    try {
                        // esperar respuesta del servidor
                        byte[] bufferResp = new byte[1024];
                        DatagramPacket respuestaPacket = new DatagramPacket(bufferResp, bufferResp.length);
                        socket.receive(respuestaPacket);

                        String respuesta = new String(respuestaPacket.getData(), respuestaPacket.getOffset(), respuestaPacket.getLength());
                        System.out.println("Respuesta del servidor: " + respuesta);
                        recibido = true; // todo correcto
                    } catch (SocketTimeoutException e) {
                        System.out.println("No hubo respuesta. Reintentando... (" + intentos + "/" + MAX_REINTENTOS + ")");
                    }
                }

                if (!recibido) {
                    System.out.println("No se recibió respuesta tras " + MAX_REINTENTOS + " intentos.");
                }
            }

            socket.close();
            scanner.close();

        }
        catch (SocketException e)
        {
            System.out.println(e.getMessage());
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
