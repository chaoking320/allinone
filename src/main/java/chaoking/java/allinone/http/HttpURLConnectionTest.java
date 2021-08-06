package chaoking.java.allinone.http;

import com.google.common.io.CharStreams;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpURLConnectionTest {
    public static void main(String[] args) {
        InputStreamReader in = null;
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) (new URL("http://localhost:8080/meta/address").openConnection());
            connection.setConnectTimeout(1000);
            connection.setReadTimeout(500);
            connection.setDoInput(true);
            in = new InputStreamReader(connection.getInputStream());
            String content = CharStreams.toString(in);
            if (connection.getResponseCode() != 200) {
                //
            }
            String msg = content.trim();

        } catch (IOException e) {

        }
    }
}
