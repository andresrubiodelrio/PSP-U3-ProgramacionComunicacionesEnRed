package urlconnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

public class URLConnectionTest3 {
    public static void main(String[] args) {
        try {
            URI uri = new URI("http://localhost/2025/vernombre.php");
            URL url = uri.toURL();

            URLConnection conexion = url.openConnection();
            conexion.setDoOutput(true);

            String cadena = "nombre=María Jesús&apellidos=Ramos Martín";

            // ESCRIBIR EN LA URL
            PrintWriter output = new PrintWriter(conexion.getOutputStream());
            output.write(cadena);
            output.close(); // cerrar flujo

            // LEER DE LA URL
            BufferedReader reader = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
            String linea;
            while ((linea = reader.readLine()) != null) {
                System.out.println(linea);
            }
            reader.close();// cerrar flujo

        } catch (MalformedURLException | URISyntaxException e) {
            System.err.println("MalformedURLException: " + e);
            System.out.println(e.getMessage());
        } catch (IOException ioe) {
            System.err.println("IOException: " + ioe);
            System.out.println(ioe.getMessage());
        }
    }// main
}
