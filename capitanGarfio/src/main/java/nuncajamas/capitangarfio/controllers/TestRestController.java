package nuncajamas.capitangarfio.controllers;


import nuncajamas.capitangarfio.feignCients.GovernatorApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import java.util.Map;

@RestController
@RequestMapping("/capitanGarfio")
public class TestRestController {

    @Autowired
    GovernatorApiClient governatorClient;

    @RequestMapping(value="/test", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String,Object> execute(){
        Map<String,Object> governatorData = governatorClient.governance();
        return governatorData;
    }
}
