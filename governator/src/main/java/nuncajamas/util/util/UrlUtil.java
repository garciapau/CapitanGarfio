package nuncajamas.util.util;

import org.springframework.cloud.client.ServiceInstance;

public class UrlUtil {

    private static final String GOVERNANCE_CONTEXT = "/governance";

    public static String buildGovernanceUrl(ServiceInstance s) {
        return "http://" + s.getHost() + ":" + String.valueOf(s.getPort()) + GOVERNANCE_CONTEXT;
    }
}
