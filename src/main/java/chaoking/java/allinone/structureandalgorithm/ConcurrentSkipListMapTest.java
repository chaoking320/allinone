package chaoking.java.allinone.structureandalgorithm;

import java.util.concurrent.ConcurrentSkipListMap;

public class ConcurrentSkipListMapTest {

    public static void main(String[] args) {

        String s = "Hello";
        s = s+"world!";

        ConcurrentSkipListMap<Long,String> map = new ConcurrentSkipListMap<>();

        map.put(1L,"1");
        map.put(2L,"2");

        map.pollFirstEntry();



        System.out.println("balabala");



    }
}
