package chaoking.java.allinone.filterchain;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by chao_w on 2024/5/10.
 */
@Controller
@RequestMapping()
public class Test {
    @RequestMapping("/test1")
    @ResponseBody
    public String test1(String a){
        System.out.printf("我是controller");
        return null;
    }
}
