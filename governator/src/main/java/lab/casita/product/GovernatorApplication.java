package lab.casita.product;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Produces;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.apache.http.HttpHeaders.USER_AGENT;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
//@Controller
@RestController
public class GovernatorApplication {

    private static final String GOVERNATOR = "GOVERNATOR";
    //	private EurekaClient eurekaClient;
    @Autowired
    private DiscoveryClient discoveryClient;

    public static void main(String[] args) {
        SpringApplication.run(GovernatorApplication.class, args);
    }

    @RequestMapping(value = "/governance", method = RequestMethod.GET)
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> governance() {
//		return eurekaClient.services();
//		return discoveryClient.getServices().stream().map(s -> s.concat(",")).collect(Collectors.joining());

        Map<String, Object> resp = new HashMap<>();
        List<String> ll = discoveryClient.getServices();

        discoveryClient.getServices()
                .stream().filter(s -> !s.equalsIgnoreCase(GOVERNATOR)).map(s -> discoveryClient.getInstances(s)
                .stream().findFirst().get())
                .forEach(s -> resp.put(s.getServiceId(), sendGet(buildUrl(s))));
        return resp;
//            return (new ObjectMapper()).writeValueAsString(resp);
    }

    private String buildUrl(ServiceInstance s) {
        return "http://" + s.getHost() + ":" + String.valueOf(s.getPort()) + "/governance";
    }

    // HTTP GET request
    private Map<String, Object> sendGet(String url) {
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
