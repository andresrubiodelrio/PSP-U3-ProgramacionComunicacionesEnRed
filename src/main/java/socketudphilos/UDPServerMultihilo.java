package socketudphilos;

import java.io.IOException;
import java.net.*;

public class UDPServerMultihilo {

    public static void main(String[] args)  {

        try
        {
            DatagramSocket socket = new DatagramSocket(5000);
            System.out.println("Servidor UDP multihilo escuchando en puerto 5000");

            byte[] buffer = new byte[1024];

            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                // Crear un hilo para procesar cada paquete
                new Thread(() -> procesarPaquete(packet)).start();
            }
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

    public static void procesarPaquete(DatagramPacket packet) {

        String mensaje = new String(packet.getData(), packet.getOffset(), packet.getLength());
        System.out.println("Procesando desde " + packet.getAddress() + ":" + packet.getPort() +
                " → " + mensaje);

        // Simular respuesta al cliente
        try {
            DatagramSocket socket = new DatagramSocket();
            String respuesta = "ACK: " + mensaje;
            byte[] datos = respuesta.getBytes();

            DatagramPacket resp = new DatagramPacket(
                    datos,
                    datos.length,
                    packet.getAddress(),
                    packet.getPort()
            );
            socket.send(resp);
            socket.close();
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
