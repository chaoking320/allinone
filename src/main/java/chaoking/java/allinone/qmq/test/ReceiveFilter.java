package chaoking.java.allinone.qmq.test;

import chaoking.java.allinone.qmq.test.model.ReceivingMessage;

public interface ReceiveFilter {
    void invoke(Invoker invoker, ReceivingMessage message);
}
