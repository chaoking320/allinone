package chaoking.java.mydemo.controller.model;

import java.io.Serializable;

public class TestModel implements Serializable {
    private Long orderId;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
