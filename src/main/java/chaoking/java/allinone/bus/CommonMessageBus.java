package chaoking.java.allinone.bus;

import net.engio.mbassy.bus.IMessagePublication;
import net.engio.mbassy.bus.MBassador;
import org.springframework.stereotype.Component;

@Component
public class CommonMessageBus<T> {
    private MBassador<T> bus;
    public CommonMessageBus() {
        bus = new MBassador<>();
    }

    public void subscribe(Object listener) {
        bus.subscribe(listener);
    }

    public void publish(T message) {
        IMessagePublication result = bus.publish(message);
    }

    public void publishAsync(T message) {
        IMessagePublication result = bus.publishAsync(message);
        if (result.hasError()) {

        }
    }
}
