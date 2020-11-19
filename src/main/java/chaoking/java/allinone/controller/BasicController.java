package chaoking.java.allinone.controller;

import chaoking.java.allinone.utils.httpclient.HttpClient;
import okhttp3.MediaType;
import org.apache.catalina.connector.RequestFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping(value = "/basic")
public class BasicController {

    @Autowired
    private HttpClient httpClient;

    @RequestMapping("/atomicRerence")
    public String hello(String name){

        httpClient.post("http://127.0.0.1:8888/basic/post");

        return String.format("hello,%s",name);
    }

    @RequestMapping(value="post", method= RequestMethod.POST)
    public String saveUser(HttpServletRequest request){
        System.out.println(request.toString());
        System.out.println(String.format("port:%s",request.getRemotePort()));
        System.out.println(String.format("uri:%s",request.getRequestURI()));
        System.out.println(String.format("remote-port:%s",request.getHeader("remote-port")));
        return String.format("hello,%s","aa");
    }
}
