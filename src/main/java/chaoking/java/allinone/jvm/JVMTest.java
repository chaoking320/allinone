package chaoking.java.allinone.jvm;

import org.openjdk.jol.info.ClassLayout;

import java.util.ArrayList;
import java.util.HashMap;

public class JVMTest {

    public static void main(String[] args) {
        People p = new People();
        p.setId(123l);
        p.setAge(18);
        p.setName("T-Superman");
        System.out.println(p.hashCode());

        // 关闭指针压缩：-XX:-UseCompressedOops 再看看
        System.out.printf(ClassLayout.parseInstance(p).toPrintable());
    }
}
