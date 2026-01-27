package socketupd;
import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.util.Scanner;

public class UDPEjemplo2Cliente {

	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		try
		{
			DatagramSocket clientSocket = new DatagramSocket();// socket cliente

			// DATOS DEL SERVIDOR al que enviar mensaje
			InetAddress IPServidor = InetAddress.getLocalHost();// localhost
			int puerto = 12345; // puerto por el que escucha

			// INTRODUCIR DATOS POR TECLADO
			System.out.print("Introduce mensaje: ");
			String cadena = sc.nextLine();

			byte[] enviados = new byte[1024];
			enviados = cadena.getBytes();

			// ENVIANDO DATAGRAMA AL SERVIDOR
			DatagramPacket envio = new DatagramPacket(enviados, enviados.length, IPServidor, puerto);
			clientSocket.send(envio);

			// RECIBIENDO DATAGRAMA DEL SERVIDOR
			byte[] recibidos = new byte[4]; // 4 bytes para un int
			DatagramPacket recibo = new DatagramPacket(recibidos, recibidos.length);
			System.out.println("Esperando datagrama del Servidor....");
			clientSocket.receive(recibo);

			// Obtener el número e caracteres. Convertir bytes a int
			int num = ByteBuffer.wrap(recibo.getData()).getInt();
			System.out.println("Recibo Nº de caracteres que son a=> " + num);

			clientSocket.close();// cerrar socket
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
