package lab.casita.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
//@Controller
@RestController
public class ShopApplication {

	@Autowired
	private ProductClient productClient;

	public static void main(String[] args) {
		SpringApplication.run(ShopApplication.class, args);
	}

	@RequestMapping(value="/getProducts", method = RequestMethod.GET)
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
	public String getProducts(Model model) {
//		model.addAttribute("products", productClient.products());
		return productClient.products();
	}

	@RequestMapping(value="/governance", method = RequestMethod.GET)
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> getGovernance() {
	    /*
            { "info":{
              "serviceId": "pepe",
              "version": "1.0"
              },
              "consumes":[
                  {"type": "service",
                   "channel": "API",
                   "id":"pepa",
                   "version": "1.0"},
                  {"type": "resource",
                   "channel": "TOPIC",
                  "id": "topic.anna",
                  "version": "1.0"}
              ],
               "produces":[
                  {"type":"resource",
                  "channel": "TOPIC",
                  "id": "topic.pau",
                  "version": "1.0"},
                  {"channel": "API",
                   "type": "service",
                  "id":"pepa",
                  "version": "1.0"}
              ]
            }
	     */
		Map<String, Object> response = new HashMap<>();
		Map<String, Object> info = new HashMap<>();
		info.put("serviceId", "shop");
		info.put("version", "1.0.0");
		response.put("info", info);

		Map<String, Object> consumes = new HashMap<>();
		consumes.put("type", "service");
		consumes.put("channel", "api");
		consumes.put("id", "product");
		consumes.put("version", "1.0.0");
		response.put("consumes", consumes);

		return response;
	}}
