package nuncajamas.governator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class GovernatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(GovernatorApplication.class, args);
    }
}
