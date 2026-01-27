package socketupd;

import java.io.IOException;
import java.net.*;


public class UDPClientMultiClient {
    public static void main(String[] args) {
        try
        {
            DatagramSocket socket = new DatagramSocket(); // puerto automático
            InetAddress serverAddr = InetAddress.getByName("localhost");

            String mensaje = "Hola desde cliente " + socket.getLocalPort();
            byte[] buffer = mensaje.getBytes();

            DatagramPacket packet =
                    new DatagramPacket(
                            buffer,
                            buffer.length,
                            serverAddr,
                            5000
                    );

            socket.send(packet);

            // Recibir respuesta
            byte[] responseBuffer = new byte[1024];
            DatagramPacket responsePacket =
                    new DatagramPacket(
                            responseBuffer,
                            responseBuffer.length
                    );

            socket.receive(responsePacket);

            String respuesta = new String(
                    responsePacket.getData(),
                    0,
                    responsePacket.getLength()
            );

            System.out.println("Respuesta del servidor: " + respuesta);

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
