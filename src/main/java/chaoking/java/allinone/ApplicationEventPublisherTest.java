package chaoking.java.allinone;

import chaoking.java.allinone.model.SpringEventModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationEventPublisherTest {

    /**
     * Spring 事件发布者
     */
    @Autowired
    private ApplicationEventPublisher publisher;

    public void test(){

        SpringEventModel model = new SpringEventModel();
        model.setContent("test.content");
        model.setEventType("test.event");
        publisher.publishEvent(model);
    }

}
