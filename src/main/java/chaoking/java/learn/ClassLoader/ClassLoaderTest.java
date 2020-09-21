package chaoking.java.learn.ClassLoader;

import sun.misc.Launcher;

import java.io.IOException;

public class ClassLoaderTest {
    public static void main(String[] args) throws IOException {
        System.out.println(Thread.currentThread().getContextClassLoader());
        System.out.println(ClassLoaderTest.class.getClassLoader());
        System.out.println(System.class.getClassLoader());
        System.out.println(ClassLoader.getSystemClassLoader());
        System.out.println(ClassLoader.getSystemClassLoader().getParent());
        System.out.println(ClassLoader.getSystemClassLoader().getParent().getParent());
        System.in.read();

        Class<?> intClass = int.class;
        Class<?> integerClass = Integer.class;
        Class<Integer> integerClass2 = Integer.class;
        Class<Number> numberClass = Number.class;
        Class<? extends Number> numberClass2 = Integer.class;
        //Class<Number> numberClass3 = Integer.class;
    }
}
