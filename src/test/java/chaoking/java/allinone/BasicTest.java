package chaoking.java.allinone;

import chaoking.java.allinone.basic.AtomicReference.People;
import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class BasicTest {

    private static People p1 = new People("Tom", 12);
    private static People p2 = new People("Jack", 13);
    private static AtomicReference<People> ar = new AtomicReference(p1);
    private static AtomicReference<People> at = new AtomicReference(p1);
    private static AtomicStampedReference<People> asr = new AtomicStampedReference(p1, 0);

    @Test
    public  void atomicReferenceTest(){
        People people = ar.get();
        System.out.println(people == p1);//true

        System.out.println(ar.compareAndSet(null, p2));//false
        System.out.println(ar.get());//People{name='Tom', age=12}

        System.out.println(ar.compareAndSet(p1, p2));//true
        System.out.println(ar.get());//People{name='Jack', age=13}
    }

    @Test
    public void test(){
        Thread t1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                at.compareAndSet(p1, p2);
                at.compareAndSet(p2, p1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();

        Thread t2 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                boolean b = at.compareAndSet(p1, p2);
                System.out.println("AtomicReference " + b);//AtomicReference true
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t2.start();

        Thread t3 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                asr.compareAndSet(p1, p2, asr.getStamp(), asr.getStamp() + 1);
                asr.compareAndSet(p2, p1, asr.getStamp(), asr.getStamp() + 1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t3.start();

        Thread t4 = new Thread(() -> {
            try {
                int stamp = asr.getStamp();
                TimeUnit.SECONDS.sleep(2);
                boolean b = asr.compareAndSet(p1, p2, stamp, stamp + 1);
                System.out.println("AtomicStampedReference " + b);//AtomicStampedReference false
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t4.start();
    }
}
