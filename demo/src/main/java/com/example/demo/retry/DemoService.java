package com.example.demo.retry;

import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

// 5. DemoService.java - 演示业务类
@Service
public class DemoService {
    private final AtomicInteger counter = new AtomicInteger();

    @Retryable(maxAttempts = 3)
    public String unreliableMethod() {
        if (counter.incrementAndGet() % 3 != 0) {
            throw new RuntimeException("模拟失败");
        }
        return "成功执行！";
    }
}
