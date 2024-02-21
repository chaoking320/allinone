package chaoking.java.allinone.learn.thread;

//import sun.nio.ch.ThreadPool;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.lang.management.OperatingSystemMXBean;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 意在说明ThreadLocal的使用场景及方法：即多个线程使用同一个对象的时候为了避免冲突，线程将对象在本地赋值，这样就做到了互不干扰
 * 但是在线程池的场景中，如果不主动remove，则可能造成内存泄露
 */
public class MyThreadLocal {

    public static void main(String[] args) {

        Person person =new Person();

        // region 简单演示ThreadLocal
        new Thread(new Runnable() {
            @Override public void run() {
                person.setName("Hello");
                try {
                    Thread.sleep(3000);
                    System.out.println("1 name 为："+person.getName());
                    // 用完即清除，避免内存溢出
                    person.remove();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override public void run() {
                person.setName("World");
                try {
                    Thread.sleep(3000);
                    System.out.println("2 name 为："+person.getName());
                    // 用完即清除，避免内存溢出
                    person.remove();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        //  endregion

        ThreadPoolExecutor executor =
            new ThreadPoolExecutor(10, 20, 0L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(5), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

        BigClass big = new BigClass();

        OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        // 椎内存使用情况
        MemoryUsage memoryUsage = memoryMXBean.getHeapMemoryUsage();

        int index = 0;
        for (Integer i=0;i<25;i++){
            index++;
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    person.setBigClass(big);
                    System.out.println("当前内存占用："+memoryMXBean.getHeapMemoryUsage().getUsed());
                }
            });
        }
    }

    static class Person{
        private ThreadLocal<String> name = new ThreadLocal<>();
        private ThreadLocal<BigClass> bigClass = new ThreadLocal<>();

        public void setName(String name){
            this.name.set(name);
        }

        public String getName(){
            return this.name.get();
        }

        /**
         * 避免线程池中使用时内存溢出，故需要主动remove
         */
        public void remove(){
            this.name.remove();
            this.bigClass.remove();
        }

        public void setBigClass(BigClass big) {
            this.bigClass.set(big);
        }

        public BigClass getBigClass(){
            return this.bigClass.get();
        }
    }

    static class BigClass{
        private String[] arrays;
        public BigClass() {
            arrays = new String[1024 * 1024 * 1024];
        }
    }
}
