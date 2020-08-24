package chaoking.java.allinone.model;

import org.springframework.context.ApplicationEvent;

public class SpringEventModelCopy  extends ApplicationEvent {
    public String eventType;
    public String content;

    public SpringEventModelCopy(Object source) {
        super(source);
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
