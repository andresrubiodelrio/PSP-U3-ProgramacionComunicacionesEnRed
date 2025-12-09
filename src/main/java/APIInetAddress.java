import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class APIInetAddress {
    public static void main(String[] args) {
        InetAddress direccion = null;
        System.out.println("========================================================");
        System.out.println("SALIDA PARA LOCALHOST: ");
        try {
            //LOCALHOST
            direccion = InetAddress.getByName("localhost");
            pruebaMetodosInetAddress(direccion);//

            //URL	www.google.es
            System.out.println("========================================================");
            System.out.println("SALIDA PARA UNA URL: iesalandalus.org");
            direccion = InetAddress.getByName("iesalandalus.org");
            pruebaMetodosInetAddress(direccion);

            // Array de tipo InetAddress con todas las direcciones IP
            //asignadas a google.es
            InetAddress direccionGoogle=InetAddress.getByName("www.google.es");;
            System.out.println("========================================================");
            System.out.println("\tDIRECCIONES IP PARA: " + direccionGoogle.getHostName());
            InetAddress[] direcciones = InetAddress.getAllByName(direccionGoogle.getHostName());

            for (int i = 0; i < direcciones.length; i++)
                System.out.println("\t\t"+direcciones[i].toString());

            System.out.println("========================================================");



        }
        catch (UnknownHostException e)
        {
            System.out.println(e.getMessage());
        }
    }// main

    private static void pruebaMetodosInetAddress(InetAddress direccion) {

        System.out.println("\tMetodo getByName():  " + direccion);

        InetAddress dir2;
        try {
            dir2 = InetAddress.getLocalHost();
            System.out.println("\tMetodo getLocalHost(): " + dir2);
        }
        catch (UnknownHostException e)
        {
            System.out.println(e.getMessage());
        }

        // USAMOS METODOS DE LA CLASE
        System.out.println("\tMetodo getHostName(): "+direccion.getHostName());

        System.out.println("\tMetodo getHostAddress(): "+ direccion.getHostAddress());

        for(byte b:direccion.getAddress())
        {
            int valor = b & 0xFF; //Enmascaramiento para convertir el byte en un entero sin signo entre 0 y 255.
            System.out.println("Byte: " + valor);
        }

        System.out.println("\tMetodo toString(): " + direccion.toString());

        //Método isReachable(timeout en milisegundos)
        // Esperaremos máximo 3 segundos (3000 ms)
        boolean alcanzable = false;
        try {
            alcanzable = direccion.isReachable(3000);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.err.println("ERROR: No se puede encontrar el host.");
            System.err.println("Verifica que el nombre esté bien escrito.");
        }

        if (alcanzable) {
            System.out.println("ÉXITO: La máquina es alcanzable y responde.");
        } else {
            System.out.println("FALLO: La máquina no responde o está bloqueando la petición.");
        }

    }
}
