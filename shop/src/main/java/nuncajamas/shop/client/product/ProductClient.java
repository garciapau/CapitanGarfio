package nuncajamas.shop.client.product;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("product")
public interface ProductClient {
    @RequestMapping("/products")
    String products();
}
