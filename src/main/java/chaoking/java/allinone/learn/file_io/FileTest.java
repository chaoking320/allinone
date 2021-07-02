package chaoking.java.allinone.learn.file_io;

import io.netty.buffer.ByteBuf;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;

public class FileTest {
    public static void main(String[] args) throws IOException {

        // netty里面的
        //ByteBuf


        //MappedByteBuffer
        // java 字节缓冲区
        //ByteBuffer
        //Buffer
        String msg = "hello";
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put(msg.getBytes());

        // 字节数组
        //byte[]


        File file = new File("D:\\data\\test.txt");
        RandomAccessFile rFile = new RandomAccessFile(file, "rw");

        //write1();
        //write2();

    }


    private static void write1() throws IOException {
        File file = new File("D:\\data\\test.txt");

        String str = "helloWorld from write1";

        FileWriter fileWritter = new FileWriter("D:\\data\\test.txt",true);
        fileWritter.write(str);
        fileWritter.close();
    }

    private static void write2() throws IOException {
        String content = "helloWorld from write2";

        File file = new File("D:\\data\\test.txt");
        FileOutputStream fop = new FileOutputStream(file);
        byte[] contentInBytes = content.getBytes();
        fop.write(contentInBytes);
        fop.flush();
        fop.close();
    }

    private static void write3() throws IOException {
        File file = new File("D:\\data\\test.txt");

        ByteBuffer buff = ByteBuffer.allocate(1024);
        String str = "helloWorld";
        buff.put(str.getBytes());

        FileWriter fileWritter = new FileWriter(file.getName(),true);
        fileWritter.write(str);
        //fileWritter.write(buff);
        fileWritter.close();
    }
}
