package com.example.demo.retry;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

// 4. RetryAspect.java - 注解处理 AOP
@Aspect
@Component
public class RetryAspect {
    @Autowired
    private RetryDispatcher retryDispatcher;

    @Around("@annotation(retryable)")
    public Object around(ProceedingJoinPoint joinPoint, Retryable retryable) throws Throwable {
        String taskId = UUID.randomUUID().toString();
        CompletableFuture<Object> future = new CompletableFuture<>();
        RetryTask task = new RetryTask(taskId, joinPoint, retryable.maxAttempts(), future);
        retryDispatcher.enqueue(task);

        try {
            return future.get(5, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            throw new RuntimeException("重试超时");
        }
    }
}
