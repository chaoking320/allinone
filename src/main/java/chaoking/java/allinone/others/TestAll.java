package chaoking.java.allinone.others;

import org.openjdk.jol.info.ClassLayout;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * Created by chao_w on 2019/1/24.
 */
@Component
public  class  TestAll {

    /**
     * 验证Long和long的区别
     */
    public static void Test4Long(){
        Long value = 55L;
        if(value.equals(55L)){
            System.out.print("true");
        }else{
            System.out.print("false");
        }
    }

    /**
     * 验证Long和long的区别
     */
    public static void Test4HashMap(){
        int i = 1<<4;

        HashMap<String,String> map = new HashMap<>();
        map.put("a","1");
    }

    /**
     * 对象布局
     */
    public static void TestJOL(){
        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        synchronized (o){
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
    }
}


















