package com.example.demo.retry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 6. DemoController.java - 控制器
@RestController
@RequestMapping("/api")
public class DemoController {
    @Autowired
    private DemoService demoService;

    @GetMapping("/test")
    public String test() {
        String msg = demoService.unreliableMethod();
        return  msg;
    }
}