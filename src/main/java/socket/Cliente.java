package socket;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {

    private static final String host = "localhost";
    private int puerto;

    public Cliente (int numPuerto) {
        setPuerto(numPuerto);
    }

    public int getPuerto() {
        return puerto;
    }

    public void setPuerto(int puerto) {
        if (puerto<=0)
            throw new IllegalArgumentException("ERROR: El número de puerto no es correcto.");

        this.puerto = puerto;
    }

    // para iniciar el cliente
    public void iniciar ( ) {
        try{
            // Me conecto al servidor en un determinado puerto
            Socket sCliente = new Socket( host, puerto );

            // TAREAS QUE REALIZA EL  CLIENTE
            System.out.println ("\nCliente conectado al servidor " + host + "por el puerto " + puerto);

            //Comunicación con el servidor: Transmisión y recepción de informacióna través de flujos.

            // Cierro el socket
            sCliente.close();
        }
        catch (UnknownHostException e){
            System.out.println(e);
        }
        catch( IOException e ) {
            System.out.println( e.getMessage() );
        }
    } // fin iniciar
}
