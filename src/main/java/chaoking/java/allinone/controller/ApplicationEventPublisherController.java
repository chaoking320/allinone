package chaoking.java.allinone.controller;

import chaoking.java.allinone.ApplicationEventPublisherTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationEventPublisherController {

    @Autowired
    private ApplicationEventPublisherTest test;

    @RequestMapping("/publisher")
    public void test(){
        test.test();
    }
}
