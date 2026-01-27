package sockettcphilos;

import java.io.*;
import java.net.*;

public class HiloServidor extends Thread {

	private Socket cliente;

	public HiloServidor(Socket socket) {
		this.cliente = socket;
	}

	public void run() { // tarea a realizar con el cliente

		try (
				// se crean flujos de entrada y salida
				ObjectOutputStream  fsalida = new ObjectOutputStream(cliente.getOutputStream());
				ObjectInputStream  fentrada = new ObjectInputStream (cliente.getInputStream());
			) {

			String cadena = "";
			System.out.println("COMUNICO CON: " + cliente.toString());

			while (!cadena.trim().equals("*")) {
				// Leer cadena enviada por el cliente
				cadena = (String) fentrada.readObject();
				System.out.println("Cadena recibida: " + cadena);

				// Enviar respuesta al cliente
				fsalida.writeObject(cadena.trim().toUpperCase());// enviar mayuscula
				fsalida.flush();
			} 

			System.out.println("FIN CON: " + cliente.toString());

		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Cliente desconectado.");
		} finally {
			try {
				cliente.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}// ..
