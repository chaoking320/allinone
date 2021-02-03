package chaoking.java.allinone.qmq.test.model;

import com.google.common.util.concurrent.SettableFuture;

public class ReceivingMessage {
    private final RawMessage message;
    private final SettableFuture<ReceiveResult> promise;

    private final long receivedTime;

    public ReceivingMessage(RawMessage message, long receivedTime) {
        this.message = message;
        this.receivedTime = receivedTime;
        this.promise = SettableFuture.create();
    }

    public RawMessage getMessage() {
        return message;
    }

    public SettableFuture<ReceiveResult> promise() {
        return promise;
    }

    public long getReceivedTime() {
        return receivedTime;
    }

    public String getMessageId() {
        return message.getHeader().getMessageId();
    }

    public void done(ReceiveResult result) {
        promise.set(result);
    }

    public String getSubject() {
        return message.getHeader().getSubject();
    }

    public boolean isExpired() {
        return System.currentTimeMillis() > message.getHeader().getExpireTime();
    }

    public boolean isHigh() {
        return message.isHigh();
    }
}
