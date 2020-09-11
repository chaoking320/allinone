package chaoking.java.learn.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReetranLockDemo {
    public static void main(String[] args) throws Exception{
        ExecutorService executorService = Executors.newCachedThreadPool();
        ReentrantLock reentrantLock = new ReentrantLock();
        int[] count = {0};
        int num = 0;

        for (int i=0;i<10000;i++){
            executorService.submit(()->{
                try{
                    reentrantLock.lock();
                    count[0]++;
                    System.out.println(num);
                }catch(Exception e){
                    e.printStackTrace();
                }finally {
                    reentrantLock.unlock();
                }
            });
        }
        reentrantLock.unlock();
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.HOURS);
        System.out.println(count);
    }
}
