package urlconnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.List;
import java.util.Map;

public class URLConnectionTest2 {
    public static void main(String[] args) {
        String cadena;

        URI uri = null;
        try {
            uri = new URI("http://www.iesalandalus.org");

            URL url = null;

            url = uri.toURL();

            URLConnection conexion = null;

            conexion = url.openConnection();

            System.out.println("Direccion [getURL()]:" + conexion.getURL());

            //LocalDate fecha = new LocalDate();
            System.out.println("Fecha ultima modificacion [getLastModified()]: " + conexion.getLastModified());
            System.out.println("Tipo de Contenido [getContentType()]: " + conexion.getContentType());

            System.out.println("============================================ ");
            System.out.println("TODOS LOS CAMPOS DE CABECERA CON getHeaderFields(): ");

            //USAMOS UNA ESTRUCTURA Map PARA RECUPERAR CABECERAS
            Map<String, List<String>> camposcabecera = conexion.getHeaderFields();
            for (Map.Entry<String, List<String>> entry : camposcabecera.entrySet()) {
                String clave = entry.getKey(); // Nombre del campo
                List<String> valores = entry.getValue(); // Lista de valores del campo

                System.out.println(clave + " : " + valores);
            }

            System.out.println("============================================ ");
            System.out.println("CAMPOS 1 Y 4 DE CABECERA:");
            System.out.println("getHeaderField(1)=> " + conexion.getHeaderField(1));
            System.out.println("getHeaderField(4)=> " + conexion.getHeaderField(4));
            System.out.println("============================================");

            System.out.println("CONTENIDO DE [url.getFile()]:" + url.getFile());
            BufferedReader pagina = new BufferedReader(new InputStreamReader(url.openStream()));

            while ((cadena = pagina.readLine()) != null) {
                System.out.println(cadena);
            }
        }
        catch (URISyntaxException | MalformedURLException e) {
            System.out.println(e.getMessage());
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
