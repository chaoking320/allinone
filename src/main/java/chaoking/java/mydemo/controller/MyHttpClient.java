package chaoking.java.mydemo.controller;


import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 自己写的http请求，支持跨域访问
 * tomcat中部署的一个服务http://localhost:8080/MyDemo/test/中的callback4post这个接口
 * 由本地启动的test2的callback4post这个接口请求，验证访问成功
 */
@Component
public class MyHttpClient {

    public String getResponse(Long orderId,String url,String data) throws IOException {
       String responseMsg = postCore(url,data);
       return responseMsg;
    }

    private String postCore(String url,String jsonData) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpClientContext context = HttpClientContext.create();
        HttpPost request = new HttpPost(url);
        StringEntity entity = new StringEntity(jsonData,"UTF-8");
        entity.setContentType("application/json");
        request.setEntity(entity);
        CloseableHttpResponse response =httpClient.execute(request, context);
        if (response != null && response.getEntity() != null) {
            return EntityUtils.toString(response.getEntity());
        }
        return "";
    }

}
