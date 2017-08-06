package nuncajamas.capitangarfio.feignCients;


import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@FeignClient("governator")
public interface GovernatorApiClient {

    @RequestMapping(value = "/governance", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    Map<String, Object> governance();

}
