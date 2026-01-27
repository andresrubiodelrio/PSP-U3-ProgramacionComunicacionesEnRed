package socketupd;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class ReceptorUDP {

    public static void main(String[] args) {
        byte[] mensaje=new byte[1024]; //array de bytes que va a contener el mensaje a enviar
        int puerto=12345;
        try
        {
            //Se crea un objeto de tipo DatagramPacket para recibir el mensaje
            DatagramPacket dpRecibido=new DatagramPacket(mensaje, mensaje.length);

            //Se crea el socket para recibir el datagrama
            DatagramSocket socket=new DatagramSocket(puerto);

            System.out.println("Socket escuchando al puerto: " +  puerto);
            socket.receive(dpRecibido); //Se recibe el datagrama a través del socket creado.

            int bytesRecibidos=dpRecibido.getLength(); //Número de bytes recibidos en el mensaje
            String cadena=new String(dpRecibido.getData()); //Obtenemos el contenido del mensaje

            System.out.println("Número de bytes recibidos: " + bytesRecibidos);
            System.out.println("Contenido del mensaje: " + cadena.trim());
            System.out.println("IP origen del datagrama: " + dpRecibido.getAddress().getHostAddress());
            System.out.println("Puerto origen del datagrama: " + dpRecibido.getPort());
            System.out.println("IP destino del datagrama: " + socket.getLocalAddress().getHostAddress());
            System.out.println("Puerto destino del datagrama: " + socket.getLocalPort());

            socket.close();


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
