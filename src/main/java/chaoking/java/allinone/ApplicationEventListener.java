package chaoking.java.allinone;

import chaoking.java.allinone.model.SpringEventModel;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationEventListener {

    @EventListener
    public void onEvent(SpringEventModel event){
        System.out.println("哈哈哈哈");
    }
}
