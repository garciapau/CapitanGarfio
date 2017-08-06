package nuncajamas.util.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import static org.apache.http.HttpHeaders.USER_AGENT;

/**
 * Created by U6023035 on 06/08/2017.
 */
public class HttpUtil {
    // HTTP GET request
    public static Map<String, Object> sendGet(String url) {
        HttpURLConnection con = null;
        StringBuffer response = new StringBuffer();
        JsonNode j = null;
        try {
            URL obj = new URL(url);
            con = (HttpURLConnection) obj.openConnection();
            // optional default is GET
            con.setRequestMethod("GET");

            //add request header
            con.setRequestProperty("User-Agent", USER_AGENT);

            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return new ObjectMapper().readValue(response.toString(), Map.class);
        } catch (IOException e) {
            System.out.println(url + " not reachable");
            return null;
        }
    }
}
