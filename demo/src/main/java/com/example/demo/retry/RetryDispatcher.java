package com.example.demo.retry;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.io.Console;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

// 3. RetryDispatcher.java - 任务队列与线程池调度器
@Component
public class RetryDispatcher {
    private final BlockingQueue<RetryTask> queue = new LinkedBlockingQueue<>();
    private final ExecutorService executor = Executors.newFixedThreadPool(4);

    @PostConstruct
    public void start() {
        executor.submit(() -> {
            while (true) {
                System.out.println(queue.size());
                RetryTask task = queue.take();

                System.out.println("2:"+task);

                executor.submit(() -> {
                    Object result = null;
                    for (int i = 0; i < task.maxAttempts; i++) {
                        try {
                            result = task.joinPoint.proceed();
                            break;
                        } catch (Throwable e) {
                            try {
                                Thread.sleep(200);
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                    if (result == null) {
                        task.future.completeExceptionally(new RuntimeException("重试失败"));
                    } else {
                        task.future.complete(result);
                    }
                });
            }
        });
    }

    public void enqueue(RetryTask task) {
        boolean success =queue.offer(task);
        System.out.println(success);
        System.out.println("1:"+task);
    }
}