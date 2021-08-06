package chaoking.java.qmqTest.files;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.common.io.LineReader;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;

public class CheckPointFileTest {
    public static void main(String[] args) throws IOException {


        File file = new File("D:\\data\\messagelog\\00000000000000000000");
        final byte[] data = Files.toByteArray(file);
        final LineReader reader = new LineReader(new StringReader(new String(data, Charsets.UTF_8)));
        System.out.println("hh");

    }

    private static void checkpointtest() throws IOException {
        File storePath = new File("D:\\data\\checkpoint");
        final File[] files = storePath.listFiles((a, name) -> name.startsWith("message-checkpoint."));
        final int beginIndex = "message-checkpoint.".length();
        for (final File file:files){
            long version = Long.parseLong(file.getName().substring(beginIndex));
            final byte[] data = Files.toByteArray(file);
            final LineReader reader = new LineReader(new StringReader(new String(data, Charsets.UTF_8)));
            System.out.println("hh");
        }
    }
}
