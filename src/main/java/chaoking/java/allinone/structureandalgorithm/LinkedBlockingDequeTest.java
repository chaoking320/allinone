package chaoking.java.allinone.structureandalgorithm;

import java.util.concurrent.LinkedBlockingDeque;

/**
 * 双向阻塞队列
 */
public class LinkedBlockingDequeTest {
    public static void main(String[] args) {

        LinkedBlockingDeque<String> blockingDeque = new LinkedBlockingDeque<>();
        blockingDeque.add("A");
        blockingDeque.addFirst("a");
        blockingDeque.addLast("Z");

        // 取出队列中最后一个节点，原队列不变
        String t = blockingDeque.peekLast();

        // 取出队列中最后一个节点，并从原队列中删除，原队列改变
        String tt = blockingDeque.pollLast();

    }
}
