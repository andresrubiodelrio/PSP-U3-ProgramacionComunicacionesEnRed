package sockettcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    private int puerto=2200;

    // constructor
    public Servidor(int numPuerto) {

        setPuerto(numPuerto);
    } // fin constructor

    public int getPuerto() {
        return puerto;
    }

    public void setPuerto(int puerto) {
        if (puerto<=0)
            throw new IllegalArgumentException("ERROR: El número de puerto no puede ser negativo.");

        this.puerto = puerto;
    }

    public void iniciar () {
        ServerSocket serverSocket = null;
        try {
            // Inicio la escucha del servidor en un determinado puerto
            serverSocket = new ServerSocket(puerto);
            System.out.println("Escucho por el puerto " + puerto );

            // Espero a que se conecte un cliente y creo un nuevo socket para el cliente
            Socket sCliente = serverSocket.accept();

            // ATENDER PETICIÓN DEL CLIENTE
            System.out.println ("Cliente conectado....");

            //Transmisión y recepción de información a través de flujos

            // Cierro el socket del cliente
            sCliente.close();

            System.out.println ("Cerrada la conexión con el Cliente.");

        } catch( IOException e ) {
            System.out.println( e.getMessage() );
        }finally{
            // Cerrar el socket servidor
            if (serverSocket != null) //ServerSocket
                try{
                    serverSocket.close();
                }catch (Exception e) {
                    System.err.println("Error al cerrar ServerSocket.");
                    System.out.println( e.getMessage() );
                }
        }

    } // fin iniciar
}
