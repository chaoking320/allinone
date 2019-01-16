package chaoking.java.allinone.bus;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BusConfiguration {
    /**
      * 初始化注册监听
     * @param eventBus
     * @return*/
    @Bean
    public SubscriberProcessor subscriberProcessor(CommonMessageBus eventBus) {
        return new SubscriberProcessor(eventBus);
    }
}
