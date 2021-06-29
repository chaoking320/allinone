package chaoking.java.allinone.learn.transaction;

import chaoking.java.allinone.learn.transaction.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {
    @Autowired
    private Producer producer;
    @Autowired
    private TransactionTest transactionTest;

    @RequestMapping("/hh")
    public String handler(){

        transactionTest.handler(producer);
        //producer.sendMessage("balabala");
        return "success";
    }
}
