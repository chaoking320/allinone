package chaoking.java.allinone.learn.transaction;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class TransactionTest {

    @Transactional
    public void handler(Producer producer){
        producer.sendMessage("balabala");
    }

}
