package chaoking.java.allinone.learn.file_io;

import io.netty.buffer.ByteBuf;
import org.apache.zookeeper.server.ByteBufferInputStream;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class ByteBufferTest {

    public static void main(String[] args) throws IOException {
        File file = new File("D:\\data\\messagelog\\00000000000000000000");

        RandomAccessFile rawFile = new RandomAccessFile(file, "rw");
        // 1
        byte[] data=new byte[1024*1024];
        rawFile.read(data);
        String msgs =new String(data,"utf-8").trim();
        ByteBuffer buffers = ByteBuffer.wrap(data);

        Charset charset = Charset.forName("utf-8");
        CharsetDecoder decoder = charset.newDecoder();
        CharBuffer charBuffer = null;
         charBuffer = decoder.decode(buffers);//用这个的话，只能输出来一次结果，第二次显示为空
        //charBuffer = decoder.decode(buffers.asReadOnlyBuffer());
        msgs = charBuffer.toString();

        // 2
        FileChannel channel =  rawFile.getChannel();
        MappedByteBuffer mappedByteBuffer = channel
            .map(FileChannel.MapMode.READ_ONLY, 0, channel.size());


        byte[] bytes = new byte[1024];
        StringBuffer sb = new StringBuffer();
        FileInputStream in = new FileInputStream(file);
        int len;
        while ((len = in.read(bytes)) != -1) {
            sb.append(new String(bytes, 0, len));
        }
        System.out.println(sb.toString());

       // byteBufferTest();
    }

    private static void byteBufferTest(){
        ByteBuffer buff = ByteBuffer.allocate(1024);
        String str = "helloWorld";
        buff.put(str.getBytes());
        System.out.println(new String(buff.array()));
        System.out.println("position:" + buff.position() + "\t limit:"
            + buff.limit() + "\t capacity:" + buff.capacity());
        // 读取两个字节byte[] abytes = new byte[1];
        byte[] abytes = new byte[1];
        buff.get(abytes);
        System.out.println("get one byte to string:" + new String(abytes));
        // Reads the byte at this buffer's current position, and then increments
        // the position.
        buff.get();
        System.out.println("获取两个字节（两次get()方法调用）后");
        System.out.println("position:" + buff.position() + "\t limit:"
            + buff.limit());
        // Sets this buffer's mark at its position. like
        // ByteBuffer.this.mark=position
        buff.mark();
        System.out.println("mark()...");
        System.out.println("position:" + buff.position() + "\t limit:"
            + buff.limit());

        // 当读取到码流后，进行解码。首先对ByteBuffer进行flip操作，
        // 它的作用是将缓冲区当前的limit设置为position,position设置为0
        // flip方法将Buffer从写模式切换到读模式。调用flip()方法会将position设回0，并将limit设置成之前position的值。
        buff.flip();
        System.out.println("flip()...");
        System.out.println("position:" + buff.position() + "\t limit:"
            + buff.limit() + "\t capacity:" + buff.capacity());

        byte[] tbyte = new byte[buff.limit()];
        buff.get(tbyte);
        System.out.println("get one byte to string:" + new String(tbyte));
        System.out.println("position:" + buff.position() + "\t limit:"
            + buff.limit());
        if (buff.hasRemaining()) {
            buff.compact();
        } else {
            buff.clear();
        }
    }
}
