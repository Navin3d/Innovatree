package gmc.project.innovatree;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class InnovatreeApplication {

	public static void main(String[] args) {
		SpringApplication.run(InnovatreeApplication.class, args);
	}

}
