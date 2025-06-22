package chaoking.java.mydemo.springbootinit;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class RunBeanDemo {

    @PostConstruct
    public void testRun(){
        System.out.printf("******************PostConstruct*************");
    }
}
