package chaoking.java.allinone.learn.thread.executortest;

import chaoking.java.allinone.learn.thread.NamedThreadFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Test {
    public static void main(String[] args) {

        ExecutorService pullExecutor = Executors.newCachedThreadPool(new NamedThreadFactory("qmq-pull"));
        Future<?> future = pullExecutor.submit(new Executor2Test());
        System.out.println(123);
    }
}
