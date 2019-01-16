package chaoking.java.allinone.bus;

import net.engio.mbassy.listener.Handler;

@Subscriber
public class BusListener2 {
    @Handler
    public void handle(String msg){
        System.out.println(String.format("我收到了2，%s",msg));
    }

    @Handler
    public void handle1(String msg){
        System.out.println(String.format("我收到了字符2，%s",msg));
    }

    @Handler
    public void handle2(Integer number){
        System.out.println(String.format("我收到了数字2，%s",number));
    }
}
