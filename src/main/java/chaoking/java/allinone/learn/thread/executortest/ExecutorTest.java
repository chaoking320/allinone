package chaoking.java.allinone.learn.thread.executortest;

import chaoking.java.allinone.learn.thread.NamedThreadFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorTest implements Runnable {

    private final Executor executor;
    private final Executor executor2;

    public ExecutorTest(Executor executor,Executor executor2) {
        this.executor = executor;
        this.executor2 = executor2;
    }

    @Override
    public void run() {
        Thread thread = Thread.currentThread();
        String threadName = thread.getName();
        int a = 1+1;
        int b = 2+a;
        System.out.println(b);
        System.out.println("进入 ExecutorTest 中的 run 方法"+ threadName);
    }

    public void start(){
        executor.execute(this);
    }

    public void start2(){
        executor2.execute(this);
    }

    private void this_test(String msg){
        Executor e =  this.executor;
        System.out.println(e.getClass()+msg);
    }

    public static void main(String[] args) {
        ExecutorService pullExecutor = Executors.newCachedThreadPool(new NamedThreadFactory("executortest"));
        ExecutorService pullExecutor2 = Executors.newCachedThreadPool(new NamedThreadFactory("executortest2"));
        ExecutorTest test = new ExecutorTest(pullExecutor,pullExecutor2);
        test.start();
        test.start2();

        ExecutorTest t = new ExecutorTest(pullExecutor,pullExecutor2);
        t.this_test("this 测试 ");
    }
}
