package lab.casita.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableAutoConfiguration
//@EnableDiscoveryClient
@SpringBootApplication
@EnableEurekaClient
@RestController
public class ProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> products() {
        Map<String, Object> response = new HashMap<>();
        response.put("prod1", "Product 1");
        return response;
    }

    @RequestMapping(value="/governance", method = RequestMethod.GET)
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> getGovernance() {
        Map<String, Object> response = new HashMap<>();

        response.put("usedBy", "shop");
	    return response;
    }
}
