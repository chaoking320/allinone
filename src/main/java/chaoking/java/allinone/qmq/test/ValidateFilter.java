package chaoking.java.allinone.qmq.test;

import chaoking.java.allinone.qmq.test.model.ReceivingMessage;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

public class ValidateFilter implements ReceiveFilter{
    @Override
    public void invoke(Invoker invoker, ReceivingMessage message) {
        Preconditions.checkNotNull(message, "message not null");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(message.getMessageId()), "message id should not be empty");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(message.getSubject()), "message subject should not be empty");
        invoker.invoke(message);
    }
}
