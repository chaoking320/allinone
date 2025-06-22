package chaoking.java.structures_algorithms;

import java.util.*;

public class One {

    public void test_hashmap(){
        HashMap<String,String> map = new HashMap<>();
        map.put("a","a");
        map.put("b","b");
        map.put("c","c");
        map.put("d","d");
        map.put("e","e");
        map.put("f","f");
    }

    public void test_arraylist(){
        ArrayList<String> array = new ArrayList<>();
        array.add("a");

    }

    public void test_linkedlist(){
        LinkedList<String> array = new LinkedList<>();
        array.add("a");

    }

    static int i;
    static Integer h;

    public static void main(String[] args) {

        System.out.println(i);
        System.out.println(h);

        char[] chars = "wang".toCharArray();
        for (char c:chars) {
            System.out.println(String.format("%s:%s",c,(int)c));
        }
    }


}
