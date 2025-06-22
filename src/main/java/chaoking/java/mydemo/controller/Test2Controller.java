package chaoking.java.mydemo.controller;

import chaoking.java.mydemo.controller.model.TestModel;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;


@Controller
@RequestMapping("/test2")
public class Test2Controller {
    @Autowired
    private MyHttpClient myHttpClient;


    @RequestMapping(value = "/callback4post", method = RequestMethod.POST)
    @ResponseBody
    public TestModel callback4Post(@RequestBody TestModel model) throws IOException {
        String jsonData = JSON.toJSONString(model);
        String msg = myHttpClient.getResponse(0l,"http://localhost:8080/MyDemo/test/callback4post",jsonData);


        System.out.print("来了");
        TestModel t = new TestModel();
        t.setOrderId(model.getOrderId());
        return t;
    }

    @RequestMapping(value = "/callback4get", method = RequestMethod.GET)
    @ResponseBody
    public TestModel callback4Get(Long orderId) {
        System.out.print("来了"+orderId.toString());
        TestModel t = new TestModel();
        t.setOrderId(orderId);
        return t;
    }


    @RequestMapping(value = "/callback4jsonp", method = RequestMethod.GET)
    @ResponseBody
    public String callback4Jsonp(Long orderId) throws IOException {
        System.out.print("来了"+orderId.toString());
        TestModel t = new TestModel();
        t.setOrderId(orderId);
        //String msg = SerializeUtils.serialize(t);
        String msg ="({\"userId\":\"E540365921\"})";
        return msg;
    }
}
