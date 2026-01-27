package sockettcphilos;

import java.io.*;
import java.net.*;

public class Cliente {
	public static void main(String[] args)  {
		String host = "localhost";
		int puerto = 6000;// puerto remoto

		try (Socket cliente = new Socket(host, puerto);
				// CREO FLUJO DE SALIDA AL SERVIDOR				
				ObjectOutputStream  fsalida = new ObjectOutputStream(cliente.getOutputStream());				
				// CREO FLUJO DE ENTRADA AL SERVIDOR
				ObjectInputStream  fentrada = new ObjectInputStream (cliente.getInputStream());
				// FLUJO PARA ENTRADA ESTANDAR
				BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		) {

			System.out.println("Conectado al servidor.");

			String cadena, eco = "";

			do {
				System.out.print("Introduce cadena: ");
				cadena = in.readLine();
				fsalida.writeObject(cadena);
				fsalida.flush();

				// Recibir respuesta del servidor
				eco = (String) fentrada.readObject();
				System.out.println("  =>ECO: " + eco);
			} while (!cadena.trim().equals("*"));

			fsalida.close();
			fentrada.close();
			System.out.println("Fin del envío... ");
			in.close();
			cliente.close();

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		} 

	}//
}//
