package chaoking.java.allinone.learn.file_io;


import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Scanner;
import java.util.concurrent.atomic.DoubleAdder;
import java.util.concurrent.atomic.LongAdder;

public class RandomAccessFileTest {


    public static void main(String[] args) throws IOException {

        // mappedbytebuffertest();


        File file = new File("D:\\data\\test.txt");
        RandomAccessFile rfile = new RandomAccessFile(file,"rw");

        // 当前文件的内容位置
        final long currentPosition = rfile.length();
        /**
         * 切记这里实例化MappedByteBuffer的时候已经将当前文件的开始位置传入
         * 故，新写入的内容不会覆盖原文件的内容
         */
        MappedByteBuffer mbb =rfile.getChannel().map(FileChannel.MapMode.READ_WRITE,currentPosition, 1500);

        String msg = "哈哈哈哈";
        ByteBuffer byteBuffer = ByteBuffer.wrap(msg.getBytes("utf-8"));
        // 这里put之后 文件已经变化了
        mbb.put(byteBuffer);

        new LongAdder().add(1L);
        new DoubleAdder().add(1);


        // region 写文件

/*        // 游标移至1024位置
        rFlie.seek(1024);
        // 写入信息(注意这里是强行覆盖)
        rFlie.write("从第1024开始".getBytes());
        // 关闭文件
        rFlie.close();*/

      /*  // endregion
        rfile.seek(1024);
        int i = rfile.read();
        // region 读文件
*/
        byte[] bytes  = new byte[5];
        bytes[0] = (byte) 228;
        bytes[1] = (byte) 187;
        bytes[2] = (byte) 142;
        bytes[3] = (byte) 231;
        bytes[4] = (byte) 172;

        String str = new String(bytes);

        // endergion
    }


    /**
     * mappedbytebuffer 读取文件内容
     */
    private static void mappedbytebuffertest(){
        File file = new File("D:\\data\\test.txt");
        long len = file.length();
        byte[] ds = new byte[(int) len];

        try {
            MappedByteBuffer mappedByteBuffer = new RandomAccessFile(file, "r")
                .getChannel()
                .map(FileChannel.MapMode.READ_ONLY, 0, len);
            for (int offset = 0; offset < len; offset++) {
                byte b = mappedByteBuffer.get();
                ds[offset] = b;
            }

            Scanner scan = new Scanner(new ByteArrayInputStream(ds)).useDelimiter(" ");
            while (scan.hasNext()) {
                System.out.print(scan.next() + " ");
            }

        } catch (IOException e) {}

    }

}
