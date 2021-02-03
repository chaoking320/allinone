package chaoking.java.allinone.qmq.test;

import chaoking.java.allinone.qmq.test.model.ReceivingMessage;

public interface Invoker {
    void invoke(ReceivingMessage message);
}
