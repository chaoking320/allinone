package chaoking.java.allinone.controller;

import chaoking.java.allinone.bus.CommonMessageBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BusController {
    @Autowired
    private CommonMessageBus bus;

    @RequestMapping("/bushello")
    public String bushello(){
        bus.publish("1");
        bus.publishAsync("2");
        bus.publish(100);
        return "success";
    }
}
