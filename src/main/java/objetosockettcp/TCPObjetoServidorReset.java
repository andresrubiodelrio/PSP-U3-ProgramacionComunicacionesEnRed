package objetosockettcp;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPObjetoServidorReset {

    public static void main(String[] args) {

        try
        {
            int numeroPuerto = 6000;// Puerto
            ServerSocket servidor =  new ServerSocket(numeroPuerto);
            System.out.println("Esperando al cliente.....");
            Socket cliente = servidor.accept();

            // Se prepara un flujo de salida para objetos
            ObjectOutputStream outObjeto = new ObjectOutputStream(
                    cliente.getOutputStream());

            // Se prepara un objeto y se envia
            Persona per = new Persona("Juan", 20);
            outObjeto.writeObject(per); //enviando objeto
            System.out.println("Envio al cliente: " + per);

            // Se obtiene un stream para leer objetos
            ObjectInputStream inObjeto = new ObjectInputStream(cliente.getInputStream());
            Persona dato = (Persona) inObjeto.readObject();
            System.out.println("Recibo: "+ dato);


            //prueba de reset() en el cliente, recibo el objeto modificado
            dato = (Persona) inObjeto.readObject();
            System.out.println("Recibo2: "+ dato);


            // CERRAR STREAMS Y SOCKETS
            outObjeto.close();
            inObjeto.close();
            cliente.close();
            servidor.close();
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
}
