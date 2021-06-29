package chaoking.java.allinone.learn.transaction;

import chaoking.java.allinone.learn.transaction.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/dao")
public class DaoController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/hh")
    public String handler(){
        orderService.Add("hh",123L);
        return "success";
    }
}
