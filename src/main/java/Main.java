import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

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
            //System.out.println(url);
        }
        return url;
    }

    private static void savingFile(String REMOTE_SERVICE_URI) throws IOException {

        String filename = Paths.get(new URL(REMOTE_SERVICE_URI).getPath()).getFileName().toString();

        System.out.println(filename + "----------------------------");
        final CloseableHttpClient httpClient = HttpClients.createDefault();
        final HttpUriRequest httpGet = new HttpGet(REMOTE_SERVICE_URI);


        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {

            final HttpEntity entity = response.getEntity();
            // вывод полученных заголовков
            // Arrays.stream(response.getAllHeaders()).forEach(System.out::println);
            // чтение тела ответа
            String body = new String(response.getEntity().getContent().readAllBytes(), StandardCharsets.UTF_8);
            System.out.println(body);

            //создаем директорию
            File dir = new File("H:/JAVA/http2");
            File file2 = new File(dir, filename + ".txt");
            // откроем байтовый поток записи в файл
            try (FileOutputStream fos = new FileOutputStream(filename + ".txt", true)) {
                // перевод строки в массив байтов
                byte[] bytes = body.getBytes();
                // запись байтов в файл
                fos.write(bytes, 0, bytes.length);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }


        }

    }

}
