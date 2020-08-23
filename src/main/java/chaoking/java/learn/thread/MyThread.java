package chaoking.java.learn.thread;

import com.google.common.util.concurrent.ListeningScheduledExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 1、newCachedThreadPool创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
 * 2、newFixedThreadPool 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
 * 3、newScheduledThreadPool 创建一个定长线程池，支持定时及周期性任务执行。
 * 4、newSingleThreadExecutor 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
 */
@Component
public class MyThread {

    protected static final ListeningScheduledExecutorService executor1 = MoreExecutors.listeningDecorator(Executors.newScheduledThreadPool(20, new NamedThreadFactory("grey-release")));
    private Executor executor2 = Executors.newFixedThreadPool(2, new NamedThreadFactory("记录日志"));

    public void ThreadQueue(Integer i)  {
        executor2.execute(new Runnable() {
            @Override
            public void run() {
                try {

                    Thread.sleep(1000L);
                    // TODO: 2020/8/23 do you want to do

                    System.out.println(String.format("%s:记录日志%s", LocalDateTime.now(),i));

                } catch (Exception e) {
                    System.out.println("记录异常日志");
                }
            }
        });
    }
}
