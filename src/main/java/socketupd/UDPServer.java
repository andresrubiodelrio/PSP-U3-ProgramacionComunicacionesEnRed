package socketupd;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPServer {

    public static void main(String[] args) {
        int puerto=9876;
        try
        {
            //Puerto por el que escucha el servidor: 9876
            DatagramSocket serverSocket = new DatagramSocket(puerto);
            byte[] recibidos = new byte[1024];
            byte[] enviados = new byte[1024];
            String cadena;

            while (true) {
                System.out.println("Esperando datagrama.....");

                //RECIBO DATAGRAMA
                recibidos = new byte[1024];
                DatagramPacket paqRecibido = new DatagramPacket(recibidos, recibidos.length);
                serverSocket.receive(paqRecibido);
                cadena = new String(paqRecibido.getData());

                //DIRECCION ORIGEN
                InetAddress IPOrigen = paqRecibido.getAddress();
                int puertoOrigen = paqRecibido.getPort();
                System.out.println("\tOrigen: " + IPOrigen + ":" + puertoOrigen);
                System.out.println("\tMensaje recibido: " + cadena.trim());

                //CONVERTIR CADENA A MAYÚSCULA
                String mayuscula = cadena.trim().toUpperCase();
                enviados = mayuscula.getBytes();

                //ENVIO DATAGRAMA AL CLIENTE
                DatagramPacket paqEnviado = new DatagramPacket(enviados, enviados.length, IPOrigen, puertoOrigen);
                serverSocket.send(paqEnviado);

                // Condición de finalización
                if (cadena.trim().equals("*")) {
                    break;
                }

            }//Fin de while

            serverSocket.close();
            System.out.println("Socket cerrado...");
        }
        catch (SocketException e)
        {
            System.out.println(e.getMessage());
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

    }
}
