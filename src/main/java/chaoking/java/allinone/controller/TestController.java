package chaoking.java.allinone.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notifications/v2")
public class TestController {

/*    @Autowired
    public TestController(
        final Integer i) {
       this.index = i;
    }*/

    @GetMapping
    public String one(
        @RequestParam(value = "appId") String appId,
        @RequestParam(value = "ip", required = false) String clientIp) {

        String msg = String.format("appId:%s ip:%s",appId,clientIp);
        System.out.println(msg);

        return msg;
    }

/*    @GetMapping
    public String two(
        @RequestParam(value = "a") String a,
        @RequestParam(value = "b") String b,
        @RequestParam(value = "c", required = false) String c) {

        String msg = String.format("a:%s b:%s c:%s",a,b,c);
        System.out.println(msg);

        return msg;
    }*/
}
