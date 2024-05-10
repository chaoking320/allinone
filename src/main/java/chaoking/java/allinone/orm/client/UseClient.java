package chaoking.java.allinone.orm.client;

import chaoking.java.allinone.orm.IgtOrderDB;
import chaoking.java.allinone.orm.po.OrderVersionPo;
import org.springframework.stereotype.Component;

/**
 * Created by chao_w on 2024/5/10.
 */
@Component
public class UseClient {

    public void test(){
        // 查询订单号 = 70007030516 的数据
        OrderVersionPo orderVersionPo = IgtOrderDB.orderVersion.firstOrDefault(t->t.setOrderId(70007030516l));
    }
}
