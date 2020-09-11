package chaoking.java.learn.others;

import groovy.transform.Synchronized;
import org.apache.tomcat.util.collections.SynchronizedQueue;

import javax.annotation.concurrent.GuardedBy;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class HiddenIterator {
    @GuardedBy("this")
    private final Set<Integer> set = new HashSet<Integer>();
    ConcurrentHashMap m;
    Hashtable h;
    CopyOnWriteArrayList c;

    public synchronized void add(Integer i) {
        set.add(i);
    }

    public synchronized void remove(Integer i) {
        set.remove(i);
    }

    public void addTenThings() {
        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            add(i);
        }

        // 这里没有锁的话 可能会产生风险
        System.out.println("Debug:added ten elements to " + set);
    }

    public static void main(String[] args) {
        HiddenIterator h = new HiddenIterator();
        h.addTenThings();
    }
}
