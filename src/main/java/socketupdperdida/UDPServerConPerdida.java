package socketupdperdida;

import java.io.IOException;
import java.net.*;
import java.util.Random;

public class UDPServerConPerdida {

    public static void main(String[] args)  {

        try
        {
            DatagramSocket socket = new DatagramSocket(5000);
            Random rand = new Random();
            byte[] buffer = new byte[1024];

            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                // Simular pérdida del 30% de paquetes
                if (rand.nextInt(100) < 30) {
                    System.out.println("Paquete perdido de " + packet.getAddress() + ":" + packet.getPort());
                    continue;
                }

                String mensaje = new String(packet.getData(), packet.getOffset(), packet.getLength());
                System.out.println("Recibido: " + mensaje);

                // Responder al cliente
                String respuesta = "ACK: " + mensaje;
                DatagramPacket resp = new DatagramPacket(
                        respuesta.getBytes(),
                        respuesta.getBytes().length,
                        packet.getAddress(),
                        packet.getPort()
                );
                socket.send(resp);
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
}
