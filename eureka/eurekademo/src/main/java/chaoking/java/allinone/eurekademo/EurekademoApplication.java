package chaoking.java.allinone.eurekademo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekademoApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekademoApplication.class, args);
    }

}
