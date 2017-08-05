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
	public String getGovernance() {
//		model.addAttribute("products", productClient.products());
		return "{ \"uses\": \"product\"}";
	}
}
