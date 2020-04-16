import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;

public class URLClass {
    public static void main(String[] args) {
        URL url;

        {
            try {
                url = new URL("https://upload.wikimedia.org/wikipedia/commons/thumb/4/42/MilfordSound.jpg/600px-MilfordSound.jpg");
                URLConnection connection = url.openConnection();

                //получаем OutputStream, чтобы писать в него данные запроса
//                OutputStream outputStream = connection.getOutputStream();
//                outputStream.write(1);
//                outputStream.flush();

                InputStream inputStream = connection.getInputStream();
                Files.copy(inputStream, new File("C://google.png").toPath());

                InputStream inputStream2 = url.openStream();
                Files.copy(inputStream, new File("C://google2.png").toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
