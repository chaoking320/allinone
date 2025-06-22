package chaoking.java.allinone.qmq.test.model;

import io.netty.buffer.ByteBuf;

public class RawMessage {
    private final MessageHeader header;
    private final ByteBuf body;
    private final int bodySize;

    public RawMessage(MessageHeader header, ByteBuf body, int size) {
        this.header = header;
        this.body = body;
        this.bodySize = size;
    }

    public MessageHeader getHeader() {
        return header;
    }

    public ByteBuf getBody() {
        return body;
    }

    public int getBodySize() {
        return bodySize;
    }

    public void setSubject(String subject) {
        header.setSubject(subject);
    }

    public boolean isHigh() {
        return (header.getFlag() & 1) == 0;
    }
}
