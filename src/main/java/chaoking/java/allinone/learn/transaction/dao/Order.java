package chaoking.java.allinone.learn.transaction.dao;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;

@Proxy(lazy = false)
@Entity
@Table(name = "orders")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long orderId;

    private String name;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
