package chaoking.java.allinone.qmq.test;

import chaoking.java.allinone.qmq.test.model.ReceivingMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class SendMessageWorker {
    private static final Logger LOG = LoggerFactory.getLogger(SendMessageWorker.class);

    private final Invoker invoker;
    public SendMessageWorker() {
        //this.invoker = new ReceiveFilterChain().buildFilterChain(this::doInvoke);
        this.invoker = new ReceiveFilterChain().buildFilterChain(this::do1);
    }

    private void doInvoke(ReceivingMessage message) {
        LOG.info(Objects.toString(message));
    }

    private void do1(ReceivingMessage a){

    }
}
