package chaoking.java.allinone.utils.httpclient;

import okhttp3.*;
import okio.ByteString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component("proHttpClient")
public class HttpClient {

 /*   @Autowired
    @Qualifier("sysOkHttpClient")
    private OkHttpClient httpClient;

    *//**
     * get请求
     *
     * @param url
     * @return
     * @throws IOException
     *//*
    public String get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = httpClient.newCall(request).execute();
        return response.body().string(); // 返回的是string 类型，json的mapper可以直接处理
    }


    *//**
     * post请求
     *
     * @param url
     * @param param
     * @return
     * @throws IOException
     *//*
    public String post(String url, String param,MediaType mediaType) throws IOException {
        RequestBody requestBody = RequestBody.create(mediaType, ByteString.encodeUtf8(param));
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        Response response = httpClient.newCall(request).execute();
        return response.body().string();

    }*/

    public void get(String url){
        OkHttpClient client = new OkHttpClient();
        //创建一个Request
        Request request = new Request.Builder()
            .get()
            .url(url)
            .build();
        //通过client发起请求
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    // String str = response.body().string();
                }

            }
        });
    }

    public void post(String url){
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder().add("username","xiaoyi").build();
        Request request = new Request.Builder()
            .post(body)
            .url(url).
                build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    // String str = response.body().string();
                }

            }
        });
    }

}
