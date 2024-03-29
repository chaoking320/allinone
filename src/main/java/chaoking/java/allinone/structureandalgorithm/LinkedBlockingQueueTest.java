package chaoking.java.allinone.structureandalgorithm;

import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 阻塞队列
 */
public class LinkedBlockingQueueTest {
    public static void main(String[] args) throws InterruptedException {

        listTest();

        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue();
        queue.put("1");
        queue.put("2");

        System.out.println("balabala");
    }

    private static void listTest(){
        LinkedList<String> list = new LinkedList<>();

        list.push("a");
        list.offer("b");

        System.out.println("balabala");
    }
}
