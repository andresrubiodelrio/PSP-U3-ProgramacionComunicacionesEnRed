package sockettcphilos;

import java.io.*;
import java.net.*;

public class Servidor {
	public static void main(String args[])  {

		int puerto = 6000;

		try (ServerSocket servidor = new ServerSocket(puerto)) {
			System.out.println("Servidor iniciado en el puerto " + puerto);

			while (true) {
				Socket cliente = servidor.accept();// esperando cliente
				HiloServidor hilo = new HiloServidor(cliente);
				hilo.start();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}// ..