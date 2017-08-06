package nuncajamas.governator.controller;

import nuncajamas.util.util.HttpUtil;
import nuncajamas.util.util.UrlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class Governator {

    private static final String GOVERNATOR = "GOVERNATOR";
    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping(value = "/governance", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> governance() {

        Map<String, Object> catalog = new HashMap<>();
        Map<String, Object> collector = new HashMap<>();
        collector.put("id", GOVERNATOR);
        catalog.put("collector", collector);

        List<String> services = discoveryClient.getServices();

        Map<String, Object> components = new HashMap<>();
        catalog.put("components",
            discoveryClient.getServices()
                    .stream()
                        .filter(s -> !s.equalsIgnoreCase(GOVERNATOR))
                        .map(s -> discoveryClient.getInstances(s)
                            .stream().findAny().get())
                    .collect(Collectors.toMap(o -> o.getServiceId(), t -> HttpUtil.sendGet(UrlUtil.buildGovernanceUrl(t))))
        );
        return catalog;
    }

}
