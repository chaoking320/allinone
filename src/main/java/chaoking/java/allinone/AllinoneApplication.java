package chaoking.java.allinone;

import chaoking.java.allinone.others.TestAll;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AllinoneApplication {


	public static void main(String[] args) {
		TestAll.Test4HashMap();
		SpringApplication.run(AllinoneApplication.class, args);
	}

}

