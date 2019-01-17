package chaoking.java.allinone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by chao_w on 2019/1/17.
 */
@Controller
public class ThymeleafController {

    @RequestMapping("/success")
    public String success(Map<String,Object> map){
        map.put("hello","您好");
        // classpath:/templates/thymeleaf_success.html
        return "thymeleaf_success";
    }
}
