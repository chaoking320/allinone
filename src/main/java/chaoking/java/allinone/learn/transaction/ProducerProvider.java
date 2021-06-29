package chaoking.java.allinone.learn.transaction;

import chaoking.java.allinone.learn.transaction.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class ProducerProvider implements Producer {

    @Autowired
    private OrderService orderService;

    private SpringTransactionProvider springTransactionProvider;
    public void setTransactionProvider(SpringTransactionProvider transactionProvider){
        springTransactionProvider = transactionProvider;
    }

    @Transactional
    @Override
    public void sendMessage(String msg) {
        Integer i = orderService.Add("transactional",123L);
        System.out.println("sendmessage:"+i.toString());
    }
}
