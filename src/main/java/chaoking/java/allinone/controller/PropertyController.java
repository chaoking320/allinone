package chaoking.java.allinone.controller;

import chaoking.java.allinone.model.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Set;

/**
 * Created by chao_w on 2019/1/17.
 */
@RestController
public class PropertyController {
    // 获取Properties文件中配置的信息
    @Value("#{'${property.test.appid:}'.split(',')}")
    private Set<String> appIds;

    @Autowired
    private Connection connection;

    @RequestMapping("/pp")
    public String hello(String name){
        String msg1 = String.format("hello,%s", Arrays.toString(appIds.toArray())+ "\r\n");
        String msg2 = String.format("hello,%s",connection.getUsername() +"_" + connection.getPassword());

        return msg1+msg2;
    }
}
