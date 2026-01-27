package socketmulticast;

import java.net.*;

public class MCCliente {
	public static void main(String args[]) throws Exception {
		// Se crea el socket multicast
		int puerto = 12345;// Puerto multicast
		InetAddress grupo = InetAddress.getByName("225.0.0.1");// Grupo
		
		MulticastSocket ms = new MulticastSocket(puerto);

		// Nos unimos al grupo
		NetworkInterface netIf = NetworkInterface.getByName("eth1"); // Cambia según tu red
		SocketAddress mcastaddr = new InetSocketAddress(grupo, puerto);
		ms.joinGroup(mcastaddr, netIf);
		
		String msg = "";

		//
		while (!msg.trim().equals("*")) {
			byte[] buf = new byte[1024];
			// Recibe el paquete del servidor multicast
			DatagramPacket paquete = new DatagramPacket(buf, buf.length);
			ms.receive(paquete);			

			msg = new String(paquete.getData());
			System.out.println("Recibo: " + msg.trim());
		}
		ms.leaveGroup(mcastaddr, netIf); // abandonamos grupo
		ms.close(); // cierra socket
		
		System.out.println("Socket cerrado...");
	}
}
