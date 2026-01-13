package urlconnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;

public class URLConnectionTest1 {
    public static void main(String[] args) {
        URL url = null;
        URLConnection urlCon = null;
        try {
            URI uri = new URI("https://www.eltiempo.es/");
            url = uri.toURL();
            urlCon = url.openConnection();

            BufferedReader in;
            InputStream inputStream = urlCon.getInputStream();
            in = new BufferedReader(new InputStreamReader(inputStream));

            String inputLine;
            while ((inputLine = in.readLine()) != null)
                System.out.println(inputLine);

            in.close();
        }
        catch (MalformedURLException | URISyntaxException e)
        {
            System.out.println(e.getMessage());
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

    }
}
