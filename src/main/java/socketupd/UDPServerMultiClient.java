package socketupd;

import java.io.IOException;
import java.net.*;

public class UDPServerMultiClient {

    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(5000);
            System.out.println("Servidor UDP escuchando en puerto 5000");

            byte[] buffer = new byte[1024];

            while (true) {
                // Recibir datagrama
                DatagramPacket packet =
                        new DatagramPacket(buffer, buffer.length);

                socket.receive(packet);

                String mensaje = new String(
                        packet.getData(),
                        0,
                        packet.getLength()
                );

                System.out.println("Mensaje recibido de " +
                        packet.getAddress() + ":" +
                        packet.getPort() + " → " + mensaje);

                // Responder SOLO a ese cliente
                String respuesta = "ACK para " + packet.getPort();
                byte[] respuestaBytes = respuesta.getBytes();

                DatagramPacket respuestaPacket =
                        new DatagramPacket(
                                respuestaBytes,
                                respuestaBytes.length,
                                packet.getAddress(),
                                packet.getPort()
                        );

                socket.send(respuestaPacket);
            }

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
