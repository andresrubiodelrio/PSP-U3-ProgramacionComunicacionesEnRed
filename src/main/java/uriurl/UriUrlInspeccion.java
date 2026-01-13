package uriurl;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class UriUrlInspeccion {

    public static void main(String[] args) {
        URL url;
        try {
            System.out.println("Ejemplo1:");
            URI uri = null;

            uri = new URI("https://ejemplo.com/capitulo3/to/actividad?=1");

            url = uri.toURL();
            Visualizar(url);

            System.out.println("Ejemplo2:");
            uri = new URI("https", "//ejemplo.com/recursos", "seccion1");
            url = uri.toURL();
            Visualizar(url);

            System.out.println("Ejemplo3:");
            uri = new URI("https", "user:pass", "ejemplo.com", 8080, "/path/to/recurso", "query=123", "seccion1");
            url = uri.toURL();
            Visualizar(url);

        }
        catch (URISyntaxException | MalformedURLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void Visualizar(URL url) {
        System.out.println("\tURL completa: " + url.toString());
        System.out.println("\tgetProtocol(): " + url.getProtocol());
        System.out.println("\tgetHost(): " + url.getHost());
        System.out.println("\tgetPort(): " + url.getPort());
        System.out.println("\tgetFile(): " + url.getFile());
        System.out.println("\tgetUserInfo(): " + url.getUserInfo());
        System.out.println("\tgetPath(): " + url.getPath());
        System.out.println("\tgetAuthority(): " + url.getAuthority());
        System.out.println("\tgetQuery(): " + url.getQuery());
        System.out.println("\tgetDefaultPort(): "+ url.getDefaultPort());
        System.out.println("==================================================");
    }


}
