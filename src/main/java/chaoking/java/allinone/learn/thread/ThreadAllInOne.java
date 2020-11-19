package chaoking.java.allinone.learn.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Exchanger;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.*;

public class ThreadAllInOne {
    public static void main(String[] args) throws InterruptedException {
        //volatile：解决可见性：
        // volatile 的读性能消耗与普通变量几乎相同，但是写操作稍慢，因为它需要在本地代码中插入许多内存屏障指令来保证处理器不发生乱序执行。

        //Synchronized：可重入



        // 1
        Lock lock = new ReentrantLock();
        lock.newCondition();


        // 2
        Semaphore s = new Semaphore(1);
        s.release();
        s.acquire();
        s.wait();
        s.notify();

        // 3
        CountDownLatch latch = new CountDownLatch(1);
        latch.await();
        latch.countDown();
        latch.notify();

        // 4
        LockSupport.park();
        //LockSupport.unpark();

        // 5
        Exchanger<String> exchanger = new Exchanger<>();

        // 6
        ReadWriteLock rwLock = new ReentrantReadWriteLock();
        rwLock.readLock();
        rwLock.writeLock();

        //Phaser;

        // 7
        CyclicBarrier barrier =new CyclicBarrier(20);

        //Unsafe
    }
}
