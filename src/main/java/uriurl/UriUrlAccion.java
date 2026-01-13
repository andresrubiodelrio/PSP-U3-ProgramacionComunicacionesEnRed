package uriurl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class UriUrlAccion {
    public static void main(String[] args) {
        URL url = null;
        try {
            URI uri = new URI("https://www.iesalandalus.org/");
            url = uri.toURL();
        } catch (MalformedURLException | URISyntaxException e) {
            System.out.println(e.getMessage());
        }

        BufferedReader in;
        try {
            InputStream inputStream = url.openStream();
            in = new BufferedReader(new InputStreamReader(inputStream));
            String inputLine;
            while ((inputLine = in.readLine()) != null)
                System.out.println(inputLine);

            in.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
