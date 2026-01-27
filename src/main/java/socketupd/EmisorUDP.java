package socketupd;

import java.io.IOException;
import java.net.*;

public class EmisorUDP {

    public static void main(String[] args) {
        int puerto=12345; //puerto al que enviar el datagrama
        InetAddress destino= null; //Destino del datagrama. En este caso localhost
        try {
            destino = InetAddress.getLocalHost();

            byte[] mensaje = new byte[1024]; //array de bytes que va a contener el mensaje a enviar
            String saludo = "Enviando saludos!!";
            mensaje = saludo.getBytes(); //Se convierte el String a bytes para enviar el mensajes

            //Se crea un objeto de tipo DatagramPacket para enviar el mensaje
            DatagramPacket dpEnviado = new DatagramPacket(mensaje, mensaje.length, destino, puerto);

            //Se crea el socket para el envío del mensaje
            DatagramSocket socket = new DatagramSocket();

            System.out.println("Enviando datagrama de longitud: "+ mensaje.length);
            System.out.println("IP destino: " + dpEnviado.getAddress().getHostAddress());
            System.out.println("Puerto destino: " + dpEnviado.getPort());
            System.out.println("Puerto local: " + socket.getLocalPort());

            socket.send(dpEnviado); //Se envía el datagramPacket a través del socket creado.



            socket.close();
        }
        catch (UnknownHostException |SocketException e) {
            System.out.println(e.getMessage());
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
