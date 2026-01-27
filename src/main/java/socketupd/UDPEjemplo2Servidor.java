package socketupd;
import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;

public class UDPEjemplo2Servidor {

	public static void main(String[] args) {

		try
		{
			// Asocio el socket al puerto 12345
			DatagramSocket socket = new DatagramSocket(12345);

			System.out.println("Servidor Esperando Datagrama .......... ");
			DatagramPacket recibo;

			byte[] bufer = new byte[1024]; // para recibir el datagrama
			recibo = new DatagramPacket(bufer, bufer.length);
			socket.receive(recibo); //recibo datagrama

			String mensaje = new String(recibo.getData()).trim();// obtengo String

			System.out.println("Servidor Recibe: " + mensaje);

			//cuento el número de letras a
			Integer contador=0;
			for(int i=0; i < mensaje.length(); i++ )
				if(mensaje.charAt(i)=='a')
					contador++;

			// DIRECCION ORIGEN DEL MENSAJE
			InetAddress IPOrigen = recibo.getAddress();
			int puerto = recibo.getPort();

			// ENVIANDO DATAGRAMA AL CLIENTE
			System.out.println("Enviando número de apariciones de la letra a=> " + contador);

			// Convertir int a byte[]
			byte[] buffer = ByteBuffer.allocate(4).putInt(contador).array();
			DatagramPacket paquete = new DatagramPacket(buffer, buffer.length, IPOrigen, puerto);
			socket.send(paquete);


			// CERRAR STREAMS Y SOCKETS
			System.out.println("Cerrando conexión...");
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