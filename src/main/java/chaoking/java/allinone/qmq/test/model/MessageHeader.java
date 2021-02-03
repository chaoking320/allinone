package chaoking.java.allinone.qmq.test.model;

import java.util.HashSet;
import java.util.Set;

public class MessageHeader {
    private long bodyCrc;
    private byte flag;
    private long createTime;
    private long expireTime;
    private String subject;
    private String messageId;

    private Set<String> tags = new HashSet<>();

    public long getBodyCrc() {
        return bodyCrc;
    }

    public void setBodyCrc(long bodyCrc) {
        this.bodyCrc = bodyCrc;
    }

    public byte getFlag() {
        return flag;
    }

    public void setFlag(byte flag) {
        this.flag = flag;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(long expireTime) {
        this.expireTime = expireTime;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }
}
