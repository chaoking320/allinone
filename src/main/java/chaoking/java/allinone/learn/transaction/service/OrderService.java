package chaoking.java.allinone.learn.transaction.service;
import chaoking.java.allinone.learn.transaction.dao.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;


@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    public Integer Add(String name,Long orderId){
        Integer i = orderRepository.Add(name,orderId);

        // 注意由于该类用了@Transactional，所以一个失败，则全部不插入成功
        //orderRepository.Add("hh",null);

        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {

            @Override public void suspend() {

            }

            @Override public void resume() {

            }

            @Override public void flush() {

            }

            @Override
            public void beforeCommit(boolean readOnly) {
                System.out.println("beforeCommit");
            }

            @Override public void beforeCompletion() {

            }

            @Override
            public void afterCommit() {
                System.out.println("afterCommit");
            }

            @Override public void afterCompletion(int i) {

            }
        });

        return i;
    }
}
