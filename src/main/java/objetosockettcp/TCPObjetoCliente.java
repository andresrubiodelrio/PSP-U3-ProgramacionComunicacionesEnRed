package objetosockettcp;

import java.io.*;
import java.net.*;

public class TCPObjetoCliente {
	public static void main(String[] arg)  {

		String Host = "localhost";
		int Puerto = 6000;// puerto remoto

		try
		{
			System.out.println("PROGRAMA CLIENTE INICIADO....");
			Socket cliente = new Socket(Host, Puerto);

			// Flujo de entrada para objetos
			ObjectInputStream perEnt = new ObjectInputStream(cliente.getInputStream());
			// Se recibe un objeto
			Persona dato = (Persona) perEnt.readObject();// recibo objeto
			System.out.println("Recibo del servidor: " + dato);

			// Modifico el objeto
			dato.setNombre("Jose Luis");
			dato.setEdad(22);

			// FLUJO DE salida para objetos
			ObjectOutputStream perSal = new ObjectOutputStream(cliente.getOutputStream());

			// Se envia el objeto
			perSal.writeObject(dato);
			System.out.println("Envio: " + dato);


			// CERRAR STREAMS Y SOCKETS
			perEnt.close();
			perSal.close();
			cliente.close();
		}
		catch (ClassNotFoundException e)
		{
			System.out.println(e.getMessage());
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
		}

	}
}// ..
