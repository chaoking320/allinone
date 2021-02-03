package chaoking.java.allinone.qmq.test.model;

public class ReceiveResult {
    private final String messageId;
    private final int code;
    private final String remark;
    private final long endOffsetOfMessage;

    public ReceiveResult(String messageId, int code, String remark, long endOffsetOfMessage) {
        this.messageId = messageId;
        this.code = code;
        this.remark = remark;
        this.endOffsetOfMessage = endOffsetOfMessage;
    }

    public String getMessageId() {
        return messageId;
    }

    public int getCode() {
        return code;
    }

    public String getRemark() {
        return remark;
    }

    public long getEndOffsetOfMessage() {
        return endOffsetOfMessage;
    }

    @Override
    public String toString() {
        return "ReceiveResult{" +
            "messageId='" + messageId + '\'' +
            ", code=" + code +
            ", remark='" + remark + '\'' +
            ", endOffsetOfMessage=" + endOffsetOfMessage +
            '}';
    }
}
