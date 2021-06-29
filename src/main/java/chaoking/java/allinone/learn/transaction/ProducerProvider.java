package chaoking.java.allinone.learn.transaction;

import chaoking.java.allinone.learn.transaction.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

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
        // 注入事务监听
        TransactionSynchronizationManager.registerSynchronization(springTransactionProvider);
        // 事实证明，即使屏蔽下面的代码，即不执行db操作，依然会走 TransactionSynchronization 的相应方法

        Integer i = orderService.Add2("transactional",123L);
        System.out.println("sendmessage:"+i.toString());
    }
}
