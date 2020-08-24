package chaoking.java.allinone.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/basic")
public class BasicController {

    @RequestMapping("/atomicRerence")
    public String hello(String name){
        return String.format("hello,%s",name);
    }
}
