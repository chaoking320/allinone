package chaoking.java.allinone.bus;

import chaoking.java.allinone.bus.model.CommonMessageInfo;
import net.engio.mbassy.listener.Handler;

@Subscriber
public class BusListener {
    @Handler
    public void handle(String msg){
        System.out.println(String.format("我收到了1，%s",msg));
    }

    @Handler
    public void handle1(String msg){
        System.out.println(String.format("我收到了字符1，%s",msg));
    }

    @Handler
    public void handle2(Integer number){
        System.out.println(String.format("我收到了数字1，%s",number));
    }


    @Handler
    public void handle4Message(CommonMessageInfo messageInfo){
        System.out.println("object");
    }
}
