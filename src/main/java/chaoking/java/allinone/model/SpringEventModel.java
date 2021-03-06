package chaoking.java.allinone.model;

import org.springframework.context.ApplicationEvent;

public class SpringEventModel extends ApplicationEvent {
    public String eventType;
    public String content;

    public SpringEventModel(Object source) {
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
