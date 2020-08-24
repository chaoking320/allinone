package chaoking.java.allinone;

import chaoking.java.allinone.model.Connection;
import chaoking.java.allinone.model.SpringEventModel;
import chaoking.java.allinone.model.SpringEventModelCopy;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationEventListener {

    @EventListener
    public void onEvent(SpringEventModel event){
        System.out.println("哈哈哈哈");
    }

    @EventListener
    public void onEvent(SpringEventModelCopy event){
        System.out.println("哈哈哈哈");
    }
}
