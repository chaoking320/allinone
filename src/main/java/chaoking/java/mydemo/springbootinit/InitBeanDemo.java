package chaoking.java.mydemo.springbootinit;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class InitBeanDemo implements InitializingBean {

    @Override public void afterPropertiesSet() throws Exception {
        System.out.println("--------------InitializingBean init bean ------------------");
        System.out.println("");
    }
}
