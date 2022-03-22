import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.io.IOException;
import java.net.URL;


public class Main {
    public static String REMOTE_SERVICE_URI;

    public static ObjectMapper mapper = new ObjectMapper();

    public static void main(String args[]) throws IOException {
        REMOTE_SERVICE_URI = "https://api.nasa.gov/planetary/apod?api_key=NtUTiwYRL5kLmNSbmXytefehIinferlrvpDYLu0z";
        String url = urlReturn(REMOTE_SERVICE_URI);
        savingFile(url);
    }

    private static String urlReturn(String REMOTE_SERVICE_URI) throws IOException {
        final CloseableHttpClient httpClient = HttpClients.createDefault();
        String url;
        final HttpUriRequest httpGet = new HttpGet(REMOTE_SERVICE_URI);

        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {

            Nasa nasa = mapper.readValue(response.getEntity().getContent(), Nasa.class);
            System.out.println(nasa);
            url = nasa.getUrl();

        }
        return url;
    }

    private static void savingFile(String REMOTE_SERVICE_URI) throws IOException {

        String filename = Paths.get(new URL(REMOTE_SERVICE_URI).getPath()).getFileName().toString();

        final CloseableHttpClient httpClient = HttpClients.createDefault();
        final HttpUriRequest httpGet = new HttpGet(REMOTE_SERVICE_URI);
        try (BufferedInputStream in = new BufferedInputStream(new URL(REMOTE_SERVICE_URI).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(filename)) {
            byte dataBuffer[] = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }

        } catch (IOException e) {
        }


    }


}
