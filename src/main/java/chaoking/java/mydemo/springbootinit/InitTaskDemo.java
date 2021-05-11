package chaoking.java.mydemo.springbootinit;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(3)
public class InitTaskDemo implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.printf("CommandLineRunner task init**************");
        System.out.printf("");
    }
}
