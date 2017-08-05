package lab.casita.product;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

@FeignClient("eureka")
public interface EurekaClient {
    @RequestMapping("/eureka/apps")
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    String services();
}
