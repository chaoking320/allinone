package chaoking.java.allinone;

import chaoking.java.allinone.model.SpringEventModel;
import chaoking.java.allinone.model.SpringEventModelCopy;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationEventListener2 {

    @EventListener
    public void onEvent2(SpringEventModel event){
        System.out.println("哈哈哈哈2");
    }

    @EventListener
    public void onEvent(SpringEventModelCopy event){
        System.out.println("哈哈哈哈");
    }
}
