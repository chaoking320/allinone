package com.example.demo.retry;

import org.aspectj.lang.ProceedingJoinPoint;

import java.util.concurrent.CompletableFuture;

// 2. RetryTask.java - 封装任务信息
public class RetryTask {
    public final String taskId;
    public final ProceedingJoinPoint joinPoint;
    public final int maxAttempts;
    public final CompletableFuture<Object> future;

    public RetryTask(String taskId, ProceedingJoinPoint joinPoint, int maxAttempts, CompletableFuture<Object> future) {
        this.taskId = taskId;
        this.joinPoint = joinPoint;
        this.maxAttempts = maxAttempts;
        this.future = future;
    }
}
