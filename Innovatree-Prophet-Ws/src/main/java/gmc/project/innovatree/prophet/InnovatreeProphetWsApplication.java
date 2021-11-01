package gmc.project.innovatree.prophet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients
public class InnovatreeProphetWsApplication {

	public static void main(String[] args) {
		SpringApplication.run(InnovatreeProphetWsApplication.class, args);
	}

}
