package chaoking.java.allinone.others;

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
}
