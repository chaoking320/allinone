package chaoking.java;

import com.google.common.hash.HashCode;
import org.codehaus.groovy.util.HashCodeHelper;
import org.springframework.util.StopWatch;

import java.util.Timer;

public class AllInOneTest {

/*    HashMap<String,String>;
    ConcurrentHashMap<String,String>;
    AtomicInteger;*/

    private static final long TIME = 1000000000;

    public static void main(String[] args) throws InterruptedException {

        // check % 与 & 的性能对比


        andTest();

        Thread.sleep(1000);

        percentTest();

        Thread.sleep(1000);

        offsetTest();

    }

    private static void percentTest(){
        long start = System.currentTimeMillis();

        long t = 0l;
        for (long i=1 ;i<TIME;i++) {
            t = 10000%i;
        }

        System.out.println(String.format("一亿次(摩) %s ms",(System.currentTimeMillis()-start)));
    }

    private static void andTest(){
        long start = System.currentTimeMillis();

        long t = 0l;
        for (long i=1 ;i<TIME;i++) {
            t = 10000&i;
        }

        System.out.println(String.format("一亿次(与) %s ms",(System.currentTimeMillis()-start)));
    }

    private static void offsetTest(){
        long start = System.currentTimeMillis();

        long t = 0l;
        for (long i=1 ;i<TIME;i++) {
            t = 10000<<i;
        }

        System.out.println(String.format("一亿次(位移) %s ms",(System.currentTimeMillis()-start)));
    }

}
