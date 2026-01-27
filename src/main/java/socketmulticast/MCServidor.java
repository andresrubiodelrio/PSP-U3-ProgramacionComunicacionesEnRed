package socketmulticast;

import java.io.*;
import java.net.*;

public class MCServidor {
	public static void main(String args[]) throws Exception {
		// FLUJO PARA ENTRADA ESTANDAR
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		// Se crea el socket multicast.
		MulticastSocket ms = new MulticastSocket();

		int puerto = 12345;// Puerto multicast
		InetAddress grupo = InetAddress.getByName("225.0.0.1");// Grupo

		String cadena = "";

		while (!cadena.trim().equals("*")) {
			System.out.print("Datos a enviar al grupo: ");
			cadena = in.readLine();
			
			// ENVIANDO AL GRUPO					
			byte[] buffer = cadena.getBytes();												
		    DatagramPacket paquete = new DatagramPacket(buffer, buffer.length, grupo, puerto);
			ms.send(paquete);			
			
			
		}
		ms.close();// cierro socket
		System.out.println("Socket cerrado...");
	}
}
