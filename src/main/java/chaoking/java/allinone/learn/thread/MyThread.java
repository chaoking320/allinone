package chaoking.java.allinone.learn.thread;

import com.google.common.util.concurrent.ListeningScheduledExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import org.springframework.stereotype.Component;

import javax.annotation.concurrent.GuardedBy;
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

    protected static final ListeningScheduledExecutorService executor1 =
        MoreExecutors.listeningDecorator(Executors.newScheduledThreadPool(20, new NamedThreadFactory("grey-release")));
    private Executor executor2 = Executors.newFixedThreadPool(2, new NamedThreadFactory("记录日志"));

    public void ThreadQueue(Integer i) {
        executor2.execute(new Runnable() {
            @Override public void run() {
                try {

                    Thread.sleep(1000L);
                    // TODO: 2020/8/23 do you want to do

                    System.out.println(String.format("%s:记录日志%s", LocalDateTime.now(), i));

                } catch (Exception e) {
                    System.out.println("记录异常日志");
                }
            }
        });
    }

    @GuardedBy("this")
    private long value = 0;

    public synchronized long getValue(){
        return value;
    }

    public synchronized long increment(){
        if(value==Long.MAX_VALUE){
            throw new IllegalStateException("counter overflow");
        }
        return ++value;
    }

    public void others(){

    }

    /**
     * 当两个并发线程访问同一个对象object中的这个synchronized(this)同步代码块时，一个时间内只能有一个线程得到执行。另一个线程必须等待当前线程执行完这个代码块以后才能执行该代码块。
     */
    static class Thread1 implements Runnable {
        public void run() {
            synchronized (this) {
                for (int i = 0; i < 5; i++) {
                    System.out.println(Thread.currentThread().getName() + " synchronized loop " + i);
                }
            }
        }

        public static void main(String[] args) {
            Thread1 t1 = new Thread1();
            Thread ta = new Thread(t1, "A");
            Thread tb = new Thread(t1, "B");
            ta.start();
            tb.start();
        }
    }


    /**
     * 然而，当一个线程访问object的一个synchronized(this)同步代码块时，另一个线程仍然可以访问该object中的非synchronized(this)同步代码块。
     */
    static class Thread2 {
        public void m4t1() {
            synchronized (this) {
                int i = 5;
                while (i-- > 0) {
                    System.out.println(Thread.currentThread().getName() + " : " + i);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ie) {
                    }
                }
            }
        }

        public void m4t2() {
            int i = 5;
            while (i-- > 0) {
                System.out.println(Thread.currentThread().getName() + " : " + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ie) {
                }
            }
        }

        public static void main(String[] args) {
            final Thread3 myt2 = new Thread3();
            Thread t1 = new Thread(new Runnable() {
                public void run() {
                    myt2.m4t1();
                }
            }, "t1");
            Thread t2 = new Thread(new Runnable() {
                public void run() {
                    myt2.m4t2();
                }
            }, "t2");
            t1.start();
            t2.start();
        }
    }


    /**
     * 尤其关键的是，当一个线程访问object的一个synchronized(this)同步代码块时，其他线程对object中所有其它synchronized(this)同步代码块的访问将被阻塞。
     */
    static class Thread3 {
        public void m4t1() {
            synchronized (this) {
                int i = 5;
                while (i-- > 0) {
                    System.out.println(Thread.currentThread().getName() + " : " + i);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ie) {
                    }
                }
            }
        }

        //修改Thread2.m4t2()方法：
        public void m4t2() {
            synchronized (this) {
                int i = 5;
                while (i-- > 0) {
                    System.out.println(Thread.currentThread().getName() + " : " + i);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ie) {
                    }
                }
            }
        }

        public static void main(String[] args) {
            final Thread2 myt2 = new Thread2();
            Thread t1 = new Thread(new Runnable() {
                public void run() {
                    myt2.m4t1();
                }
            }, "t1");
            Thread t2 = new Thread(new Runnable() {
                public void run() {
                    myt2.m4t2();
                }
            }, "t2");
            t1.start();
            t2.start();
        }
    }


    /**
     * 第三个例子同样适用其它同步代码块。也就是说，当一个线程访问object的一个synchronized(this)同步代码块时，它就获得了这个object的对象锁。结果，其它线程对该object对象所有同步代码部分的访问都被暂时阻塞。
     */
    static class Thread4 {
        public void m4t1() {
            synchronized (this) {
                int i = 5;
                while (i-- > 0) {
                    System.out.println(Thread.currentThread().getName() + " : " + i);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ie) {
                    }
                }
            }
        }

        //修改Thread2.m4t2()方法：
        public synchronized void m4t2() {
            int i = 5;
            while (i-- > 0) {
                System.out.println(Thread.currentThread().getName() + " : " + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ie) {
                }
            }
        }

        public static void main(String[] args) {
            final Thread4 myt2 = new Thread4();
            Thread t1 = new Thread(new Runnable() {
                public void run() {
                    myt2.m4t1();
                }
            }, "t1");
            Thread t2 = new Thread(new Runnable() {
                public void run() {
                    myt2.m4t2();
                }
            }, "t2");
            t1.start();
            t2.start();
        }
    }


    /**
     * 以上规则对其它对象锁同样适用
     */
    static class Thread5 {
        class Inner {
            private void m4t1() {
                int i = 5;
                while (i-- > 0) {
                    System.out.println(Thread.currentThread().getName() + " : Inner.m4t1()=" + i);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ie) {
                    }
                }
            }

            //private Synchronized void m4t2() {
            private void m4t2() {
                int i = 5;
                while (i-- > 0) {
                    System.out.println(Thread.currentThread().getName() + " : Inner.m4t2()=" + i);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ie) {
                    }
                }
            }
        }

        private void m4t1(Inner inner) {
            synchronized (inner) { //使用对象锁
                inner.m4t1();
            }
        }

        private void m4t2(Inner inner) {
            inner.m4t2();
        }

        public static void main(String[] args) {
            final Thread5 myt3 = new Thread5();
            final Inner inner = myt3.new Inner();
            Thread t1 = new Thread(new Runnable() {
                public void run() {
                    myt3.m4t1(inner);
                }
            }, "t1");
            Thread t2 = new Thread(new Runnable() {
                public void run() {
                    myt3.m4t2(inner);
                }
            }, "t2");
            t1.start();
            t2.start();
        }
    }

}

